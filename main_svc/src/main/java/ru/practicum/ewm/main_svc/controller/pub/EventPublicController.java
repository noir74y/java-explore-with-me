package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.service.EventService;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPublicController {
    EventService eventService;

    @GetMapping
    public Iterable<EventShortDto> publicFind(@RequestParam String searchPattern,
                                              @RequestParam Iterable<Long> categories,
                                              @RequestParam Boolean paid,
                                              @RequestParam @FutureOrPresent LocalDateTime rangeStart,
                                              @RequestParam @FutureOrPresent LocalDateTime rangeEnd,
                                              @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                              @RequestParam String sort,
                                              @RequestParam(defaultValue = "0") Integer from,
                                              @RequestParam(defaultValue = "10") Integer size) {
        return eventService.publicFind(searchPattern,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size);
    }

    @GetMapping("/{id}")
    public EventFullDto publicFindById(@PathVariable @NotNull Long id) {
        return eventService.publicFindById(id);
    }
}
