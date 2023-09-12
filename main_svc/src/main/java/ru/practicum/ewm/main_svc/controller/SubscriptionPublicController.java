package ru.practicum.ewm.main_svc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.main_svc.service.SubscriptionService;

@Slf4j
@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
@Validated
public class SubscriptionPublicController {
    private final SubscriptionService subscriptionService;
}
