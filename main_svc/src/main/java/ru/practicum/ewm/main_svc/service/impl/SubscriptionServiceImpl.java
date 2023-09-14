package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.ConflictException;
import ru.practicum.ewm.main_svc.error.NotFoundException;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;
import ru.practicum.ewm.main_svc.model.entity.Subscription;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.model.util.mappers.SubscriptionMapper;
import ru.practicum.ewm.main_svc.repository.SubscriptionRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.SubscriptionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository subscriptionRepository;
    UserRepository userRepository;
    SubscriptionMapper subscriptionMapper;
    EventMapper eventMapper;

    @Override
    public SubscriptionDto createSubscription(Long userId, Long friendId) {
        subscriptionRepository
                .findByUserIdAndFriendId(userId, friendId)
                .ifPresent(subscription -> {
                    throw new ConflictException("there is such subscription already");
                });
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("there is no user with id=%d", userId)));
        var friend = userRepository
                .findById(friendId)
                .orElseThrow(() -> new NotFoundException(String.format("there is no friend with id=%d", userId)));
        return subscriptionMapper.toDto(subscriptionRepository.save(Subscription.builder().user(user).friend(friend).build()));
    }

    @Override
    public void deleteSubscription(Long userId, Long friendId) {
        var subscription = subscriptionRepository
                .findByUserIdAndFriendId(userId, friendId)
                .orElseThrow(() -> new NotFoundException("there is no subscription"));
        subscriptionRepository.delete(subscription);
    }

    @Override
    public List<SubscriptionDto> findAllSubscriptions(Long userId) {
        throwIfNoSuchUser(userId);
        return subscriptionRepository
                .findAllByUserId(userId)
                .orElse(Collections.emptyList())
                .stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendsInitiators(Long userId) {
        throwIfNoSuchUser(userId);
//        return subscriptionRepository.findAllEventsByFriendsInitiators(userId,
//                        FriendshipStatus.CONFIRMED,
//                        EventState.PUBLISHED.name())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(eventMapper::entity2eventShortDto)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendsParticipants(Long userId) {
        throwIfNoSuchUser(userId);
//        return subscriptionRepository.findAllEventsByFriendsParticipants(userId,
//                        FriendshipStatus.CONFIRMED,
//                        RequestStatus.CONFIRMED,
//                        EventState.PUBLISHED.name())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(eventMapper::entity2eventShortDto)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendInitiator(Long userId, Long friendId) {
        throwIfNoSuchUser(userId);
        throwIfNoSuchUser(friendId);
//        return subscriptionRepository.findAllEventsByFriendInitiator(userId,
//                        friendId,
//                        FriendshipStatus.CONFIRMED,
//                        EventState.PUBLISHED.name())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(eventMapper::entity2eventShortDto)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<EventShortDto> findAllEventsByFriendParticipant(Long userId, Long friendId) {
        throwIfNoSuchUser(userId);
        throwIfNoSuchUser(friendId);
//        return subscriptionRepository.findAllEventsByFriendParticipant(userId,
//                        friendId,
//                        FriendshipStatus.CONFIRMED,
//                        RequestStatus.CONFIRMED,
//                        EventState.PUBLISHED.name())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(eventMapper::entity2eventShortDto)
//                .collect(Collectors.toList());
        return null;
    }

    private void throwIfNoSuchUser(Long userId) {
        if (!userRepository.existsById(userId))
            throw new NotFoundException(String.format("there is no user with id=%d", userId));
    }
}
