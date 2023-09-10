package ru.practicum.ewm.main_svc.controller.adm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;
import ru.practicum.ewm.main_svc.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Validated
public class EventAdminController {
    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> adminFindEvents(@RequestParam(value = "users", required = false) List<Long> initiators,
                                              @RequestParam(required = false) List<String> states,
                                              @RequestParam(required = false) List<Long> categories,
                                              @RequestParam(required = false) @FutureOrPresent @DateTimeFormat(pattern = MainAppConfig.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                              @RequestParam(required = false) @FutureOrPresent @DateTimeFormat(pattern = MainAppConfig.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                              @RequestParam(defaultValue = MainAppConfig.FROM) @PositiveOrZero Integer from,
                                              @RequestParam(defaultValue = MainAppConfig.SIZE) @Positive Integer size,
                                              HttpServletRequest request) {
        log.info("GET /admin/events");
        return eventService.adminFindEvents(initiators, states, categories, rangeStart, rangeEnd, from, size, request);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto adminUpdateEvent(@PathVariable @NotNull Long eventId,
                                         @RequestBody @NotNull @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        log.info("PATCH /admin/events/{} {}", eventId, updateEventAdminRequest);
        return eventService.adminUpdateEvent(eventId, updateEventAdminRequest);
    }
}
