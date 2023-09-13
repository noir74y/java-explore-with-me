package ru.practicum.ewm.main_svc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.main_svc.service.FriendshipService;

@Slf4j
@RestController
@RequestMapping("/user/{userId}/friendship")
@RequiredArgsConstructor
@Validated
public class FriendshipPrivateController {
    private final FriendshipService friendshipService;
}
