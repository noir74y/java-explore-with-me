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
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class EventPrivateController {
    private final EventService eventService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> privateFindByUser(@PathVariable("userId") @NotNull Long initiatorId,
                                                 @RequestParam(defaultValue = "0") @NotNull Integer from,
                                                 @RequestParam(defaultValue = "10") @NotNull Integer size) {
        log.info("GET /users/{}/events {}, {}", initiatorId, from, size);
        return eventService.privateFindByUser(initiatorId, from, size);
    }

    @PostMapping("/{userId}/events")
    public EventFullDto privateCreate(@PathVariable("userId") @NotNull Long initiatorId,
                                      @RequestBody @NotNull @Valid NewEventDto newEventDto) {
        log.info("POST /users/{}/events {}", initiatorId, newEventDto);
        return eventService.privateCreate(initiatorId, newEventDto);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto privateFindById(@PathVariable("userId") @NotNull Long initiatorId,
                                        @PathVariable @NotNull Long eventId) throws Throwable {
        return eventService.privateFindById(initiatorId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto privateUpdate(@PathVariable("userId") @NotNull Long initiatorId,
                                      @PathVariable @NotNull Long eventId,
                                      @RequestBody @NotNull @Valid UpdateEventUserRequest updateEventUserRequest) {
        return eventService.privateUpdate(initiatorId, eventId, updateEventUserRequest);
    }
}
