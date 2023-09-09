package ru.practicum.ewm.main_svc.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.util.enums.*;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.model.util.mappers.LocationMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.repository.RequestRepository;
import ru.practicum.ewm.main_svc.service.EventService;
import ru.practicum.ewm.stat_svc.client.StatClient;
import ru.practicum.ewm.stat_svc.other.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.other.model.DtoHitOut;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;
    final EventMapper eventMapper;
    final CategoryRepository categoryRepository;
    final LocationMapper locationMapper;
    final RequestRepository requestRepository;
    final StatClient statClient;
    final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.name}")
    String applicationName;

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> privateFindEventsByInitiator(Long initiatorId,
                                                            Integer from,
                                                            Integer size) {
        return eventRepository
                .findAllByInitiatorId(initiatorId, PageRequest.of(from / size, size))
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventFullDto privateCreateEvent(Long initiatorId,
                                           NewEventDto newEventDto) {
        return eventMapper.entity2eventFullDto(eventRepository.save(eventMapper.newEventDto2entity(initiatorId, newEventDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public EventFullDto privateFindEventById(Long initiatorId,
                                             Long eventId) throws Throwable {
        return Optional.ofNullable(eventMapper.entity2eventFullDto(eventRepository.findByInitiatorIdAndId(initiatorId, eventId)))
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d and initiatorId=%d", initiatorId, eventId), HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public EventFullDto privateUpdateEvent(Long initiatorId,
                                           Long eventId,
                                           UpdateEventUserRequest updateEventUserRequest) throws Throwable {

        var currentEvent = Optional.ofNullable(eventRepository.findByInitiatorIdAndId(initiatorId, eventId))
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d and initiatorId=%d", initiatorId, eventId), HttpStatus.NOT_FOUND));

        if (currentEvent.getState().equals(EventState.PUBLISHED.name()) || LocalDateTime.now().plusHours(2).isAfter(currentEvent.getEventDate()))
            throw new MainEwmException("the event is not updatable", HttpStatus.CONFLICT);

        Optional.ofNullable(updateEventUserRequest.getStateAction())
                .ifPresent(newState -> currentEvent.setState(newState.equals(EventUserState.SEND_TO_REVIEW.name()) ? EventState.PENDING.name() : EventState.CANCELED.name()));

        updateEventParameters(currentEvent, updateEventUserRequest);

        return eventMapper.entity2eventFullDto(eventRepository.save(currentEvent));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventFullDto> adminFindEvents(List<Long> initiators,
                                              List<String> states,
                                              List<Long> categories,
                                              LocalDateTime rangeStart,
                                              LocalDateTime rangeEnd,
                                              Integer from,
                                              Integer size) {
        if (rangeStart.isAfter(rangeEnd))
            throw new MainEwmException("rangeStart is after rangeEnd", HttpStatus.BAD_REQUEST);

        return eventRepository
                .adminFind(initiators, states, categories, rangeStart, rangeEnd, PageRequest.of(from, size))
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventFullDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional
    public EventFullDto adminUpdateEvent(Long eventId,
                                         UpdateEventAdminRequest updateEventAdminRequest) {

        var currentEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d", eventId), HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().plusHours(1).isAfter(currentEvent.getEventDate()))
            throw new MainEwmException("it's too late to update this event", HttpStatus.CONFLICT);

        Optional.ofNullable(updateEventAdminRequest.getStateAction()).ifPresent(newState -> {
            if (currentEvent.getState().equals(EventState.PENDING.name()))
                currentEvent.setState(updateEventAdminRequest.getStateAction().equals(EventAdminState.PUBLISH_EVENT.name())
                        ? EventState.PUBLISHED.name()
                        : EventState.CANCELED.name());
            else
                throw new MainEwmException("wrong state to update this event", HttpStatus.CONFLICT);
        });

        updateEventParameters(currentEvent, updateEventAdminRequest);

        return eventMapper.entity2eventFullDto(eventRepository.save(currentEvent));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> publicFindEvents(String searchPattern,
                                                Iterable<Long> categories,
                                                Boolean paid,
                                                LocalDateTime rangeStart,
                                                LocalDateTime rangeEnd,
                                                Boolean onlyAvailable,
                                                String sort,
                                                Integer from,
                                                Integer size,
                                                HttpServletRequest request) {

        rangeStart = Optional.ofNullable(rangeStart).orElse(LocalDateTime.now());
        rangeEnd = Optional.ofNullable(rangeEnd).orElse(LocalDateTime.now().plusYears(10));

        var eventShortDtoList = eventRepository.publicFind(
                        EventState.PUBLISHED.name(),
                        searchPattern.toLowerCase(),
                        categories,
                        paid,
                        rangeStart,
                        rangeEnd,
                        PageRequest.of(from, size))
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());

        // prepare list of uri to be sent to stat-svc
        List<String> eventUris = eventShortDtoList
                .stream()
                .map(eventShortDto -> "/events/" + eventShortDto.getId())
                .collect(Collectors.toList());

        addViewAndRequestInfo(LocalDateTime.now().minusYears(10), LocalDateTime.now(), eventUris, eventShortDtoList);

        // sort event list
        eventShortDtoList.sort(sort.equals(EventSort.EVENT_DATE.name())
                ? Comparator.comparing(EventShortDto::getEventDate)
                : Comparator.comparing((EventShortDto eventShortDto) -> Optional.ofNullable(eventShortDto.getViews()).orElse(0L)));

        // save stat
        statClient.saveHit(DtoHitIn.builder()
                .app(applicationName)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now()).build());

        return eventShortDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public EventFullDto publicFindEventById(Long id, HttpServletRequest request) throws Throwable {

        var eventFullDto = eventMapper.entity2eventFullDto(eventRepository
                .findById(id)
                .orElseThrow(() -> new MainEwmException("there is no such event", HttpStatus.NOT_FOUND)));

        addViewAndRequestInfo(LocalDateTime.now().minusYears(10), LocalDateTime.now(), List.of(request.getRequestURI()), List.of(eventFullDto));

        statClient.saveHit(DtoHitIn.builder()
                .app(applicationName)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now()).build());

        return eventFullDto;
    }

    private void updateEventParameters(Event currentEvent, UpdateEventRequest updateEventRequest) {
        Optional.ofNullable(updateEventRequest.getCatId())
                .ifPresent(newCatId -> currentEvent.setCategory(categoryRepository.findById(updateEventRequest.getCatId())
                        .orElseThrow(() -> new MainEwmException(String.format("there is no category with id=%d", newCatId), HttpStatus.NOT_FOUND))));
        Optional.ofNullable(updateEventRequest.getAnnotation())
                .ifPresent(currentEvent::setAnnotation);
        Optional.ofNullable(updateEventRequest.getDescription())
                .ifPresent(currentEvent::setDescription);
        Optional.ofNullable(updateEventRequest.getEventDate())
                .ifPresent(currentEvent::setEventDate);
        Optional.ofNullable(updateEventRequest.getPaid())
                .ifPresent(currentEvent::setPaid);
        Optional.ofNullable(updateEventRequest.getParticipantLimit())
                .ifPresent(currentEvent::setParticipantLimit);
        Optional.ofNullable(updateEventRequest.getRequestModeration())
                .ifPresent(currentEvent::setRequestModeration);
        Optional.ofNullable(updateEventRequest.getTitle())
                .ifPresent(currentEvent::setTitle);
        Optional.ofNullable(updateEventRequest.getLocation()).ifPresent(newLocationDto -> {
            var newLocation = locationMapper.locationDto2entity(newLocationDto);
            newLocation.setId(currentEvent.getLocation().getId());
            currentEvent.setLocation(newLocation);
        });
    }

    private void addViewAndRequestInfo(LocalDateTime rangeStart, LocalDateTime rangeEnd, List<String> eventUris, List<EventShortDto> eventShortDtoList) {
        // initialize map to store pair "uri,views"
        Map<String, Long> eventsViewsMap = new HashMap<>();

        // get response from stat-svc
        Optional.ofNullable(statClient.getHits(rangeStart, rangeEnd, eventUris, true).getBody()).ifPresent(statSvcResponseJson -> {
            // filter out entries are not applicable for main application and populate map "uri, view"
            objectMapper.convertValue(statSvcResponseJson, new TypeReference<List<DtoHitOut>>() {
                    })
                    .stream()
                    .filter(dtoHitOut -> dtoHitOut.getApp().equals(applicationName))
                    .forEach(eventView -> eventsViewsMap.put(eventView.getUri(), eventView.getHits()));

            // add views value into list of EventShortDto
            eventShortDtoList.forEach(eventShortDto -> eventShortDto.setViews(eventsViewsMap.get("/events/" + eventShortDto.getId())));

            // prepare Map(eventId -> count of confirmed requests)
            Map<Long, Long> eventsToConfirmedRequestsMap = requestRepository
                    .findEventsToConfirmedRequestsMap(RequestStatus.CONFIRMED, eventShortDtoList
                            .stream()
                            .map(EventShortDto::getId)
                            .collect(Collectors.toList()));

            // add confirmedRequests value into list of EventShortDto
            eventShortDtoList
                    .forEach(eventShortDto -> eventShortDto
                            .setConfirmedRequests(eventsToConfirmedRequestsMap.getOrDefault(eventShortDto.getId(), null)));
        });
    }
}

