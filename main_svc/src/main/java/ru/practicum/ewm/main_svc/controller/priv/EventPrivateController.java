package ru.practicum.ewm.main_svc.controller.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventUserRequest;
import ru.practicum.ewm.main_svc.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPrivateController {
    EventService eventService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> privateFindByUser(@PathVariable @NotNull Long userId,
                                                 @RequestParam(defaultValue = "0") @NotNull Integer from,
                                                 @RequestParam(defaultValue = "10") @NotNull Integer size) {
        return eventService.privateFindByUser(userId, from, size);
    }

    @PostMapping("/{userId}/events")
    public EventFullDto privateCreate(@PathVariable @NotNull Long userId,
                                      @RequestBody @NotNull @Valid NewEventDto newEventDto) {
        return eventService.privateCreate(userId, newEventDto);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto privateFindById(@PathVariable @NotNull Long userId,
                                        @PathVariable @NotNull Long eventId) {
        return eventService.privateFindById(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto privateUpdate(@PathVariable @NotNull Long userId,
                                      @PathVariable @NotNull Long eventId,
                                      @RequestBody @NotNull @Valid UpdateEventUserRequest updateEventUserRequest) {
        return eventService.privateUpdate(userId, eventId, updateEventUserRequest);
    }
}
