package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.enums.EventSort;
import ru.practicum.ewm.main_svc.model.enums.EventState;

import java.time.LocalDateTime;

public interface EventService {
    Iterable<EventShortDto> privateFindByUser(Long userId, Integer from, Integer size);

    EventFullDto privateCreate(Long userId, NewEventDto newEventDto);

    EventFullDto privateFindById(Long userId, Long eventId);

    EventFullDto privateUpdate(Long userId, Long eventId, UpdateEventRequest updateEventRequest);

    Iterable<EventFullDto> adminFind(Iterable<Long> users,
                                     Iterable<EventState> states,
                                     Iterable<Long> categories,
                                     LocalDateTime rangeStart,
                                     LocalDateTime rangeEnd,
                                     Integer from,
                                     Integer size);

    EventFullDto adminUpdate(Long eventId, UpdateEventRequest updateEventRequest);

    Iterable<EventShortDto> publicFind(String searchPattern,
                                       Iterable<Long> categories,
                                       Boolean paid,
                                       LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd,
                                       Boolean onlyAvailable,
                                       EventSort sort,
                                       Integer from,
                                       Integer size);

    EventFullDto publicFindById(Long id);
}
