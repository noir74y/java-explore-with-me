package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.enums.EventSort;
import ru.practicum.ewm.main_svc.service.EventService;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPublicController {
    EventService eventService;

    public Iterable<EventShortDto> publicFind(String searchPattern,
                                              Iterable<Long> categories,
                                              Boolean paid,
                                              LocalDateTime rangeStart,
                                              LocalDateTime rangeEnd,
                                              Boolean onlyAvailable,
                                              EventSort sort,
                                              Integer from,
                                              Integer size) {
        return eventService.publicFind(searchPattern, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    public EventFullDto publicFindById(Long id) {
        return eventService.publicFindById(id);
    }
}
