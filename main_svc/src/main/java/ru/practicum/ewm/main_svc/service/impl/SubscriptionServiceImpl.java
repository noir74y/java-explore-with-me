package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;
import ru.practicum.ewm.main_svc.service.SubscriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionDto createSubscription(Long userId, Long friendId) {
        return null;
    }

    @Override
    public void deleteSubscription(Long userId, Long friendId) {

    }

    @Override
    public List<SubscriptionDto> findAllSubscriptions(Long userId) {
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendsInitiators(Long userId) {
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendsParticipants(Long userId) {
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendInitiator(Long userId, Long friendId) {
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendParticipant(Long userId, Long friendId) {
        return null;
    }
}
