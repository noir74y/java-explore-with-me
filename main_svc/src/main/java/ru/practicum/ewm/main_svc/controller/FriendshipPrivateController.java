package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.FriendshipDto;
import ru.practicum.ewm.main_svc.service.FriendshipService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/friendship")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipPrivateController {
    final FriendshipService friendshipService;

    @PostMapping("/{friendId}/request")
    public FriendshipDto requestFriendship(@PathVariable @NotNull Long userId,
                                           @PathVariable @NotNull Long friendId) {
        log.info("POST /users/{}/friendship/{}/request", userId, friendId);
        return friendshipService.requestFriendship(userId, friendId);
    }

    @PatchMapping("/{friendId}/confirm")
    public FriendshipDto confirmFriendship(@PathVariable @NotNull Long userId,
                                           @PathVariable @NotNull Long friendId) {
        log.info("PATCH /users/{}/friendship/{}/confirm", userId, friendId);
        return friendshipService.confirmFriendship(userId, friendId);
    }

    @DeleteMapping("/{friendId}/reject")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void rejectFriendship(@PathVariable @NotNull Long userId,
                                 @PathVariable @NotNull Long friendId) {
        log.info("DELETE /users/{}/friendship/{}/reject", userId, friendId);
        friendshipService.rejectFriendship(userId, friendId);
    }

    @DeleteMapping("/{friendId}/revoke")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void revokeFriendship(@PathVariable @NotNull Long userId,
                                 @PathVariable @NotNull Long friendId) {
        log.info("DELETE /users/{}/friendship/{}/revoke", userId, friendId);
        friendshipService.revokeFriendship(userId, friendId);
    }

    @GetMapping
    public List<FriendshipDto> findAllFriends(@PathVariable @NotNull Long userId) {
        log.info("GET /users/{}/friendship", userId);
        return friendshipService.findAllFriends(userId);
    }
}
