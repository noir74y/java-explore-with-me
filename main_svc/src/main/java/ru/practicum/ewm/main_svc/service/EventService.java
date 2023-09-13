package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<EventShortDto> privateFindEventsByInitiator(Long initiatorId,
                                                     Integer from,
                                                     Integer size);

    EventFullDto privateCreateEvent(Long initiatorId,
                                    NewEventDto newEventDto);

    EventFullDto privateFindEventById(Long initiatorId,
                                      Long eventId) throws Throwable;

    EventFullDto privateUpdateEvent(Long initiatorId,
                                    Long eventId,
                                    UpdateEventUserRequest updateEventUserRequest) throws Throwable;

    List<EventFullDto> adminFindEvents(List<Long> initiators,
                                       List<String> states,
                                       List<Long> categories,
                                       LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd,
                                       Integer from,
                                       Integer size,
                                       HttpServletRequest request);

    EventFullDto adminUpdateEvent(Long eventId,
                                  UpdateEventAdminRequest updateEventAdminRequest);

    List<EventShortDto> publicFindEvents(String searchPattern,
                                         Iterable<Long> categories,
                                         Boolean paid,
                                         LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd,
                                         Boolean onlyAvailable,
                                         String sort,
                                         Integer from,
                                         Integer size,
                                         HttpServletRequest request);

    EventFullDto publicFindEventById(Long id, HttpServletRequest request) throws Throwable;
}
