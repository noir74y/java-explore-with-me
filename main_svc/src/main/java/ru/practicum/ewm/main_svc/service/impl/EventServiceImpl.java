package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.*;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;
    EventMapper eventMapper;

    @Override
    public List<EventShortDto> privateFindByUser(Long userId,
                                                 Integer from,
                                                 Integer size) {
        return eventRepository
                .findAllById(userId, PageRequest.of(from / size, size))
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto privateCreate(Long userId,
                                      NewEventDto newEventDto) {
        return eventMapper.entity2eventFullDto(eventRepository.save(eventMapper.newEventDto2entity(userId, newEventDto)));
    }

    @Override
    public EventFullDto privateFindById(Long userId,
                                        Long eventId) {
        return null;
    }

    @Override
    public EventFullDto privateUpdate(Long userId,
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
