package ru.practicum.ewm.main_svc.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;
import ru.practicum.ewm.main_svc.model.util.enums.EventSort;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;
import ru.practicum.ewm.main_svc.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPublicController {
    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> publicFindEvents(@RequestParam(value = "text", required = false) String searchPattern,
                                          @RequestParam(required = false) List<Long> categories,
                                          @RequestParam(required = false) Boolean paid,
                                          @RequestParam(required = false) @FutureOrPresent @DateTimeFormat(pattern = MainAppConfig.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                          @RequestParam(required = false) @FutureOrPresent @DateTimeFormat(pattern = MainAppConfig.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                          @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                          @RequestParam(required = false) @ValueOfEnumConstraint(enumClass = EventSort.class) String sort,
                                          @RequestParam(defaultValue = MainAppConfig.FROM) @PositiveOrZero Integer from,
                                          @RequestParam(defaultValue = MainAppConfig.SIZE) @Positive Integer size,
                                          HttpServletRequest request) {
        log.info("GET /events");
        return eventService.publicFindEvents(searchPattern,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size,
                request);
    }

    @GetMapping("/{id}")
    public EventFullDto publicFindEventById(@PathVariable @NotNull Long id,
                                       HttpServletRequest request) throws Throwable {
        log.info("GET /events/{}", id);
        return eventService.publicFindEventById(id, request);
    }
}
