package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.service.FriendshipService;
import ru.practicum.ewm.main_svc.service.SubscriptionService;
@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionServiceImpl implements SubscriptionService {
}
