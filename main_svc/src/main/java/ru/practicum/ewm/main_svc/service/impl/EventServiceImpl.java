package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.error.EwmException;
import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.model.util.mappers.LocationMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.repository.LocationRepository;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;
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

        if (currentEvent.getState().equals(EventState.PUBLISHED) || LocalDateTime.now().plusHours(2).isAfter(currentEvent.getEventDate()))
            throw new EwmException("the event is not updatable", HttpStatus.CONFLICT);

        Optional.ofNullable(updateEventUserRequest.getAnnotation())
                .ifPresent(currentEvent::setAnnotation);
        Optional.ofNullable(updateEventUserRequest.getDescription())
                .ifPresent(currentEvent::setDescription);
        Optional.ofNullable(updateEventUserRequest.getEventDate())
                .ifPresent(currentEvent::setEventDate);
        Optional.ofNullable(updateEventUserRequest.getPaid())
                .ifPresent(currentEvent::setPaid);
        Optional.ofNullable(updateEventUserRequest.getParticipantLimit())
                .ifPresent(currentEvent::setParticipantLimit);
        Optional.ofNullable(updateEventUserRequest.getRequestModeration())
                .ifPresent(currentEvent::setRequestModeration);
        Optional.ofNullable(updateEventUserRequest.getTitle())
                .ifPresent(currentEvent::setTitle);

        Optional.ofNullable(updateEventUserRequest.getStateAction())
                .ifPresent(newState -> currentEvent.setState(EventUserState.valueOf(newState) == EventUserState.SEND_TO_REVIEW ? EventState.PENDING : EventState.CANCELED));

        Optional.ofNullable(updateEventUserRequest.getCatId())
                .ifPresent(newCatId -> currentEvent.setCategory(categoryRepository.findById(updateEventUserRequest.getCatId())
                        .orElseThrow(() -> new EwmException(String.format("there is no category with id=%d", newCatId), HttpStatus.NOT_FOUND))));

        Optional.ofNullable(updateEventUserRequest.getLocation()).ifPresent(newLocationDto -> {
            var newLocation = locationMapper.locationDto2entity(newLocationDto);
            newLocation.setId(currentEvent.getLocation().getId());
            currentEvent.setLocation(newLocation);
        });

        return eventMapper.entity2eventFullDto(eventRepository.save(currentEvent));
    }

    @Override
    public List<EventFullDto> adminFind(Iterable<Long> users,
                                        Iterable<String> states,
                                        Iterable<Long> categories,
                                        LocalDateTime rangeStart,
                                        LocalDateTime rangeEnd,
                                        Integer from,
                                        Integer size) {
        return null;
    }

    @Override
    public EventFullDto adminUpdate(Long eventId,
                                    UpdateEventAdminRequest updateEventAdminRequest) {
        return null;
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
}
