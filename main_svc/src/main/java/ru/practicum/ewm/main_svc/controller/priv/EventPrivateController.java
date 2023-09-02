package ru.practicum.ewm.main_svc.controller.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventRequest;
import ru.practicum.ewm.main_svc.service.EventService;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPrivateController {
    EventService eventService;

    public Iterable<EventShortDto> privateFindByUser(Long userId, Integer from, Integer size) {
        return eventService.privateFindByUser(userId, from, size);
    }

    public EventFullDto privateCreate(Long userId, NewEventDto newEventDto) {
        return eventService.privateCreate(userId, newEventDto);
    }

    public EventFullDto privateFindById(Long userId, Long eventId) {
        return eventService.privateFindById(userId, eventId);
    }

    public EventFullDto privateUpdate(Long userId, Long eventId, UpdateEventRequest updateEventRequest) {
        return eventService.privateUpdate(userId, eventId, updateEventRequest);
    }
}
