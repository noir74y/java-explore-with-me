package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventRequest;
import ru.practicum.ewm.main_svc.model.enums.EventState;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventAdminController {
    EventService eventService;

    public Iterable<EventFullDto> adminFind(Iterable<Long> users,
                                     Iterable<EventState> states,
                                     Iterable<Long> categories,
                                     LocalDateTime rangeStart,
                                     LocalDateTime rangeEnd,
                                     Integer from,
                                     Integer size) {
        return eventService.adminFind(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    public EventFullDto adminUpdate(Long eventId, UpdateEventRequest updateEventRequest) {
        return eventService.adminUpdate(eventId, updateEventRequest);
    }
}
