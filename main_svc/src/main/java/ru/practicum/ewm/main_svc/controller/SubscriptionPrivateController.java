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
@RequestMapping("/user/{userId}/subscription")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionPrivateController {
    final SubscriptionService subscriptionService;

    @PostMapping("/create/{friendId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SubscriptionDto createSubscription(@PathVariable @NotNull Long userId,
                                              @PathVariable @NotNull Long friendId) {
        log.info("POST /users/{}/subscription/{}/create", userId, friendId);
        return subscriptionService.createSubscription(userId, friendId);
    }

    @DeleteMapping("/delete/{friendId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable @NotNull Long userId,
                                   @PathVariable @NotNull Long friendId) {
        log.info("POST /users/{}/subscription/{}/delete", userId, friendId);
        subscriptionService.deleteSubscription(userId, friendId);
    }

    @GetMapping()
    public List<SubscriptionDto> findAllSubscriptions(@PathVariable @NotNull Long userId) {
        log.info("GET /users/{}/subscription", userId);
        return subscriptionService.findAllSubscriptions(userId);
    }

    @GetMapping("/initiators/")
    public List<EventShortDto> findAllEventsByFriendsInitiators(@PathVariable @NotNull Long userId) {
        log.info("GET /users/{}/subscription/initiators", userId);
        return subscriptionService.findAllEventsByFriendsInitiators(userId);
    }

    @GetMapping("/participants")
    public List<EventShortDto> findAllEventsByFriendsParticipants(@PathVariable @NotNull Long userId) {
        log.info("GET /users/{}/subscription/participants", userId);
        return subscriptionService.findAllEventsByFriendsParticipants(userId);
    }

    @GetMapping("/initiators/{friendId}")
    public List<EventShortDto> findAllEventsByFriendInitiator(@PathVariable @NotNull Long userId,
                                                              @PathVariable @NotNull Long friendId) {
        log.info("GET /users/{}/subscription/initiators/{}", userId, friendId);
        return subscriptionService.findAllEventsByFriendInitiator(userId, friendId);
    }

    @GetMapping("/participants/{friendId}")
    public List<EventShortDto> findAllEventsByFriendParticipant(@PathVariable @NotNull Long userId,
                                                                @PathVariable @NotNull Long friendId) {
        log.info("GET /users/{}/subscription/participants/{}", userId, friendId);
        return subscriptionService.findAllEventsByFriendParticipant(userId, friendId);
    }

}
