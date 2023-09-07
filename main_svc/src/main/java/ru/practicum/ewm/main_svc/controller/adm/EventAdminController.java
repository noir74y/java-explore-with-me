package ru.practicum.ewm.main_svc.controller.adm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.main_svc.model.util.AppConfig;
import ru.practicum.ewm.main_svc.service.EventService;

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
public class EventAdminController {
    private final EventService eventService;

    @GetMapping
    public List<EventFullDto> adminFind(@RequestParam("users") List<Long> initiators,
                                        @RequestParam List<String> states,
                                        @RequestParam List<Long> categories,
                                        @RequestParam @FutureOrPresent @DateTimeFormat(pattern = AppConfig.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                        @RequestParam @FutureOrPresent @DateTimeFormat(pattern = AppConfig.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                        @RequestParam(defaultValue = AppConfig.FROM) @PositiveOrZero Integer from,
                                        @RequestParam(defaultValue = AppConfig.SIZE) @Positive Integer size) {
        return eventService.adminFind(initiators, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto adminUpdate(@PathVariable @NotNull Long eventId,
                                    @RequestBody @NotNull @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        return eventService.adminUpdate(eventId, updateEventAdminRequest);
    }
}
