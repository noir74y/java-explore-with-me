package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;

import java.util.List;

@Service
public interface SubscriptionService {

    SubscriptionDto createSubscription(Long subscriberId, Long personId);

    void deleteSubscription(Long subscriberId, Long personId);

    List<SubscriptionDto> findAllSubscriptions(Long subscriberId);

    List<EventShortDto> findAllEventsByFriendsInitiators(Long subscriberId);

    List<EventShortDto> findAllEventsByFriendsParticipants(Long subscriberId);

    List<EventShortDto> findAllEventsByFriendInitiator(Long subscriberId, Long personId);

    List<EventShortDto> findAllEventsByFriendParticipant(Long subscriberId, Long personId);
}
