package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.error.EwmException;
import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.entity.User;
import ru.practicum.ewm.main_svc.model.util.enums.EventAdminState;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.model.util.mappers.LocationMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.repository.LocationRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;
    final EventMapper eventMapper;
    final CategoryRepository categoryRepository;
    final LocationRepository locationRepository;
    final LocationMapper locationMapper;
    private final UserRepository userRepository;

    @Override
    public List<EventShortDto> privateFindByUser(Long initiatorId,
                                                 Integer from,
                                                 Integer size) {
        return eventRepository
                .findAllByInitiatorId(initiatorId, PageRequest.of(from / size, size))
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto privateCreate(Long initiatorId,
                                      NewEventDto newEventDto) {
        return eventMapper.entity2eventFullDto(eventRepository.save(eventMapper.newEventDto2entity(initiatorId, newEventDto)));
    }

    @Override
    public EventFullDto privateFindById(Long initiatorId,
                                        Long eventId) throws Throwable {
        return Optional.ofNullable(eventMapper.entity2eventFullDto(eventRepository.findByInitiatorIdAndId(initiatorId, eventId)))
                .orElseThrow(() -> new EwmException(String.format("there is no event with id=%d and initiatorId=%d", initiatorId, eventId), HttpStatus.NOT_FOUND));
    }

    @Override
    public EventFullDto privateUpdate(Long initiatorId,
                                      Long eventId,
                                      UpdateEventUserRequest updateEventUserRequest) throws Throwable {

        var currentEvent = Optional.ofNullable(eventRepository.findByInitiatorIdAndId(initiatorId, eventId))
                .orElseThrow(() -> new EwmException(String.format("there is no event with id=%d and initiatorId=%d", initiatorId, eventId), HttpStatus.NOT_FOUND));

        if (currentEvent.getState().equals(EventState.PUBLISHED.name()) || LocalDateTime.now().plusHours(2).isAfter(currentEvent.getEventDate()))
            throw new EwmException("the event is not updatable", HttpStatus.CONFLICT);

        Optional.ofNullable(updateEventUserRequest.getStateAction())
                .ifPresent(newState -> currentEvent.setState(newState == EventUserState.SEND_TO_REVIEW.name() ? EventState.PENDING.name() : EventState.CANCELED.name()));

        updateEventParameters(currentEvent, updateEventUserRequest);

        return eventMapper.entity2eventFullDto(eventRepository.save(currentEvent));
    }

    @Override
    public List<EventFullDto> adminFind(List<Long> initiators,
                                        List<String> states,
                                        List<Long> categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Integer from,
                                        Integer size) {
        if (rangeStart.isAfter(rangeEnd))
            throw new EwmException("rangeStart is after rangeEnd", HttpStatus.BAD_REQUEST);

//        Page<Event> xxx =  eventRepository
//                .adminFind(initiators
//                        //.stream()
//                        //.map(id -> userRepository.findById(id).orElse(User.builder().build()))
//                        //.collect(Collectors.toList())
//                        , states, categories, rangeStart, rangeEnd, PageRequest.of(from, size));

        return eventRepository
                .adminFind(initiators, states, categories, rangeStart, rangeEnd, PageRequest.of(from, size))
                .getContent()
                .stream()
                .map(eventMapper::entity2eventFullDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public EventFullDto adminUpdate(Long eventId,
                                    UpdateEventAdminRequest updateEventAdminRequest) {

        var currentEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EwmException(String.format("there is no event with id=%d", eventId), HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().plusHours(1).isAfter(currentEvent.getEventDate()))
            throw new EwmException("it's too late to update this event", HttpStatus.CONFLICT);

        Optional.ofNullable(updateEventAdminRequest.getStateAction()).ifPresent(newState -> {
            if (currentEvent.getState().equals(EventState.PENDING.name()))
                currentEvent.setState(updateEventAdminRequest.getStateAction().equals(EventAdminState.PUBLISH_EVENT.name()) ? EventState.PUBLISHED.name() : EventState.CANCELED.name());
            else
                throw new EwmException("wrong state to update this event", HttpStatus.CONFLICT);
        });

        updateEventParameters(currentEvent, updateEventAdminRequest);

        return eventMapper.entity2eventFullDto(eventRepository.save(currentEvent));
    }

    @Override
    public List<EventShortDto> publicFind(String searchPattern,
                                          Iterable<Long> categories,
                                          Boolean paid,
                                          LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd,
                                          Boolean onlyAvailable,
                                          String sort,
                                          Integer from,
                                          Integer size) {
        return null;
    }

    @Override
    public EventFullDto publicFindById(Long id) {
        return null;
    }

    private void updateEventParameters(Event currentEvent, UpdateEventRequest updateEventRequest) {
        Optional.ofNullable(updateEventRequest.getCatId())
                .ifPresent(newCatId -> currentEvent.setCategory(categoryRepository.findById(updateEventRequest.getCatId())
                        .orElseThrow(() -> new EwmException(String.format("there is no category with id=%d", newCatId), HttpStatus.NOT_FOUND))));
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
}
