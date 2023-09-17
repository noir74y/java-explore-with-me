package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;
import ru.practicum.ewm.main_svc.service.FriendshipService;
import ru.practicum.ewm.main_svc.service.SubscriptionService;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/feature/{userId}/{friendId}")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeatureTestController {
    final FriendshipService friendshipService;
    final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionDto createFriendshipAndSubscription(@PathVariable @NotNull Long userId,
                                                           @PathVariable @NotNull Long friendId) {
        log.info("POST /feature/{}/{}", userId, friendId);
        friendshipService.requestFriendship(userId, friendId);
        friendshipService.confirmFriendship(friendId, userId);
        return subscriptionService.createSubscription(userId, friendId);
    }
}
