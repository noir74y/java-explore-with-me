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
import ru.practicum.ewm.main_svc.model.entity.User;
import ru.practicum.ewm.main_svc.model.util.mappers.EventMapper;
import ru.practicum.ewm.main_svc.model.util.mappers.SubscriptionMapper;
import ru.practicum.ewm.main_svc.repository.FriendshipRepository;
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
    final SubscriptionRepository subscriptionRepository;
    final FriendshipRepository friendshipRepository;
    final UserRepository userRepository;
    final SubscriptionMapper subscriptionMapper;
    final EventMapper eventMapper;

    @Override
    public SubscriptionDto createSubscription(Long subscriberId, Long personId) {
        subscriptionRepository
                .findBySubscriberIdAndPersonId(subscriberId, personId)
                .ifPresent(subscription -> {
                    throw new ConflictException("there is such subscription already");
                });

        throwIfNoSuchFriendship(subscriberId, personId);

        return subscriptionMapper.toDto(subscriptionRepository
                .save(Subscription.builder()
                        .subscriber(getUserOrThrowIfAbsent(subscriberId))
                        .person(getUserOrThrowIfAbsent(personId))
                        .build()));
    }

    @Override
    public void deleteSubscription(Long subscriberId, Long personId) {
        var subscription = subscriptionRepository
                .findBySubscriberIdAndPersonId(subscriberId, personId)
                .orElseThrow(() -> new NotFoundException("there is no subscription"));

        subscriptionRepository.delete(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionDto> findAllSubscriptions(Long subscriberId) {
        throwIfNoSuchUser(subscriberId);

        return subscriptionRepository
                .findAllBySubscriberId(subscriberId)
                .orElse(Collections.emptyList())
                .stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> findAllEventsByFriendsInitiators(Long subscriberId) {
        throwIfNoSuchUser(subscriberId);

        return subscriptionRepository.findAllEventsByPersonsInitiators(subscriberId)
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> findAllEventsByFriendsParticipants(Long subscriberId) {
        throwIfNoSuchUser(subscriberId);

        return subscriptionRepository.findAllEventsByPersonsParticipants(subscriberId)
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> findAllEventsByFriendInitiator(Long subscriberId, Long personId) {
        throwIfNoSuchUser(subscriberId);
        throwIfNoSuchUser(personId);

        return subscriptionRepository.findAllEventsByPersonInitiator(subscriberId, personId)
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> findAllEventsByFriendParticipant(Long subscriberId, Long personId) {
        throwIfNoSuchUser(subscriberId);
        throwIfNoSuchUser(personId);

        return subscriptionRepository.findAllEventsByPersonParticipant(subscriberId, personId)
                .orElse(Collections.emptyList())
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toList());
    }

    private void throwIfNoSuchUser(Long subscriberId) {
        if (!userRepository.existsById(subscriberId))
            throw new NotFoundException(String.format("there is no user with id=%d", subscriberId));
    }

    private void throwIfNoSuchFriendship(Long friend1, Long friend2) {
        if (!friendshipRepository.existsByFriend1IdAndFriend2Id(friend1, friend2))
            throw new NotFoundException("there is no such friendship");
    }

    private User getUserOrThrowIfAbsent(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("there is no user with id=%d", userId)));
    }
}
