package ru.practicum.ewm.main_svc.service.impl;

import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventRequest;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;

public class EventServiceImpl implements EventService {
    @Override
    public Iterable<EventShortDto> privateFindByUser(Long userId,
                                                     Integer from,
                                                     Integer size) {
        return null;
    }

    @Override
    public EventFullDto privateCreate(Long userId,
                                      NewEventDto newEventDto) {
        return null;
    }

    @Override
    public EventFullDto privateFindById(Long userId,
                                        Long eventId) {
        return null;
    }

    @Override
    public EventFullDto privateUpdate(Long userId,
                                      Long eventId,
                                      UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public Iterable<EventFullDto> adminFind(Iterable<Long> users,
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
                                    UpdateEventRequest updateEventRequest) {
        return null;
    }

    @Override
    public Iterable<EventShortDto> publicFind(String searchPattern,
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