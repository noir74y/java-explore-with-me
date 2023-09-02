package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventRequest;

import java.time.LocalDateTime;

@Service
public interface EventService {
    Iterable<EventShortDto> privateFindByUser(Long userId,
                                              Integer from,
                                              Integer size);

    EventFullDto privateCreate(Long userId,
                               NewEventDto newEventDto);

    EventFullDto privateFindById(Long userId,
                                 Long eventId);

    EventFullDto privateUpdate(Long userId,
                               Long eventId,
                               UpdateEventRequest updateEventRequest);

    Iterable<EventFullDto> adminFind(Iterable<Long> users,
                                     Iterable<String> states,
                                     Iterable<Long> categories,
                                     LocalDateTime rangeStart,
                                     LocalDateTime rangeEnd,
                                     Integer from,
                                     Integer size);

    EventFullDto adminUpdate(Long eventId,
                             UpdateEventRequest updateEventRequest);

    Iterable<EventShortDto> publicFind(String searchPattern,
                                       Iterable<Long> categories,
                                       Boolean paid,
                                       LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd,
                                       Boolean onlyAvailable,
                                       String sort,
                                       Integer from,
                                       Integer size);

    EventFullDto publicFindById(Long id);
}
