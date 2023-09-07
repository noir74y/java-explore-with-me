package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface EventService {
    List<EventShortDto> privateFindByUser(Long initiatorId,
                                          Integer from,
                                          Integer size);

    EventFullDto privateCreate(Long initiatorId,
                               NewEventDto newEventDto);

    EventFullDto privateFindById(Long initiatorId,
                                 Long eventId) throws Throwable;

    EventFullDto privateUpdate(Long initiatorId,
                               Long eventId,
                               UpdateEventUserRequest updateEventUserRequest) throws Throwable;

    List<EventFullDto> adminFind(Iterable<Long> users,
                                 Iterable<String> states,
                                 Iterable<Long> categories,
                                 LocalDateTime rangeStart,
                                 LocalDateTime rangeEnd,
                                 Integer from,
                                 Integer size);

    EventFullDto adminUpdate(Long eventId,
                             UpdateEventAdminRequest updateEventAdminRequest);

    List<EventShortDto> publicFind(String searchPattern,
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
