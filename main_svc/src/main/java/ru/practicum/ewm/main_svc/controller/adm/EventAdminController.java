package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.main_svc.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventAdminController {
    EventService eventService;

    @GetMapping
    public Iterable<EventFullDto> adminFind(@RequestParam Iterable<Long> users,
                                            @RequestParam Iterable<String> states,
                                            @RequestParam Iterable<Long> categories,
                                            @RequestParam @FutureOrPresent LocalDateTime rangeStart,
                                            @RequestParam @FutureOrPresent LocalDateTime rangeEnd,
                                            @RequestParam(defaultValue = "0") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size) {
        return eventService.adminFind(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto adminUpdate(@PathVariable @NotNull Long eventId,
                                    @RequestBody @NotNull @Valid UpdateEventAdminRequest updateEventAdminRequest) {
        return eventService.adminUpdate(eventId, updateEventAdminRequest);
    }
}
