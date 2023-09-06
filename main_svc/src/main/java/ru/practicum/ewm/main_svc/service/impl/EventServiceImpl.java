package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.error.EwmException;
import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;
    final EventMapper eventMapper;
    final UserRepository userRepository;

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
                .orElseThrow((Supplier<Throwable>) () -> new EwmException(String.format("there is no event with id=%d and initiatorId=%d", initiatorId, eventId), HttpStatus.NOT_FOUND));
    }

    @Override
    public EventFullDto privateUpdate(Long initiatorId,
                                      Long eventId,
                                      UpdateEventUserRequest updateEventUserRequest) {
        return null;
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
