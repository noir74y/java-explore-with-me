package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;

import java.util.List;

@Service
public interface SubscriptionService {

    SubscriptionDto createSubscription(Long userId, Long friendId);

    void deleteSubscription(Long userId, Long friendId);

    List<SubscriptionDto> findAllSubscriptions(Long userId);

    List<EventShortDto> findAllEventsByFriendsInitiators(Long userId);

    List<EventShortDto> findAllEventsByFriendsParticipants(Long userId);

    List<EventShortDto> findAllEventsByFriendInitiator(Long userId, Long friendId);

    List<EventShortDto> findAllEventsByFriendParticipant(Long userId, Long friendId);
}
