package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;
import ru.practicum.ewm.main_svc.service.SubscriptionService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{subscriberId}/subscription")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionPrivateController {
    final SubscriptionService subscriptionService;

    @PostMapping("/{personId}/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SubscriptionDto createSubscription(@PathVariable @NotNull Long subscriberId,
                                              @PathVariable @NotNull Long personId) {
        log.info("POST /users/{}/subscription/{}/create", subscriberId, personId);
        return subscriptionService.createSubscription(subscriberId, personId);
    }

    @DeleteMapping("/{personId}/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable @NotNull Long subscriberId,
                                   @PathVariable @NotNull Long personId) {
        log.info("POST /users/{}/subscription/{}/delete", subscriberId, personId);
        subscriptionService.deleteSubscription(subscriberId, personId);
    }

    @GetMapping()
    public List<SubscriptionDto> findAllSubscriptions(@PathVariable @NotNull Long subscriberId) {
        log.info("GET /users/{}/subscription", subscriberId);
        return subscriptionService.findAllSubscriptions(subscriberId);
    }

    @GetMapping("/initiators/")
    public List<EventShortDto> findAllEventsByFriendsInitiators(@PathVariable @NotNull Long subscriberId) {
        log.info("GET /users/{}/subscription/initiators", subscriberId);
        return subscriptionService.findAllEventsByFriendsInitiators(subscriberId);
    }

    @GetMapping("/initiators/{personId}")
    public List<EventShortDto> findAllEventsByFriendInitiator(@PathVariable @NotNull Long subscriberId,
                                                              @PathVariable Long personId) {
        log.info("GET /users/{}/subscription/initiators/{}", subscriberId, personId);
        return subscriptionService.findAllEventsByFriendInitiator(subscriberId, personId);
    }

    @GetMapping("/participants")
    public List<EventShortDto> findAllEventsByFriendsParticipants(@PathVariable @NotNull Long subscriberId) {
        log.info("GET /users/{}/subscription/participants", subscriberId);
        return subscriptionService.findAllEventsByFriendsParticipants(subscriberId);
    }

    @GetMapping("/participants/{personId}")
    public List<EventShortDto> findAllEventsByFriendParticipant(@PathVariable @NotNull Long subscriberId,
                                                                @PathVariable Long personId) {
        log.info("GET /users/{}/subscription/participants/{}", subscriberId, personId);
        return subscriptionService.findAllEventsByFriendParticipant(subscriberId, personId);
    }

}
