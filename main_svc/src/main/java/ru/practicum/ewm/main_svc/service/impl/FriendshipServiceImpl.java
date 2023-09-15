package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.ConflictException;
import ru.practicum.ewm.main_svc.error.NotFoundException;
import ru.practicum.ewm.main_svc.model.dto.FriendshipDto;
import ru.practicum.ewm.main_svc.model.entity.Friendship;
import ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus;
import ru.practicum.ewm.main_svc.model.util.mappers.FriendshipMapper;
import ru.practicum.ewm.main_svc.repository.FriendshipRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.FriendshipService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipServiceImpl implements FriendshipService {
    final FriendshipRepository friendshipRepository;
    final UserRepository userRepository;
    final FriendshipMapper friendshipMapper;

    @Override
    public FriendshipDto requestFriendship(Long userId, Long friendId) {
        var friendshipOptional = friendshipRepository.findByFriend1IdAndFriend2Id(userId, friendId);

        friendshipOptional.ifPresent(friendship -> {
            if (friendship.getStatus().equals(FriendshipStatus.PENDING))
                throw new ConflictException("your request for friendship is still pending");
            else if (friendship.getStatus().equals(FriendshipStatus.CONFIRMED))
                throw new ConflictException("your request for friendship is confirmed already");
        });

        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("there is no user with id=%d", userId)));
        var friend = userRepository
                .findById(friendId)
                .orElseThrow(() -> new NotFoundException(String.format("there is no friend with id=%d", userId)));

        return friendshipMapper.toDto(friendshipRepository.save(Friendship.builder().friend1(user).friend2(friend).status(FriendshipStatus.PENDING).build()));
    }

    @Override
    public FriendshipDto confirmFriendship(Long userId, Long friendId) {
        var friendship = friendshipRepository
                .findByFriend1IdAndFriend2Id(userId, friendId)
                .orElseThrow(() -> new NotFoundException("there is no such friendship entry"));

        if (friendship.getStatus().equals(FriendshipStatus.CONFIRMED))
            throw new ConflictException("your request for friendship is confirmed already");
        else if (friendship.getStatus().equals(FriendshipStatus.PENDING))
            friendship.setStatus(FriendshipStatus.CONFIRMED);

        return friendshipMapper.toDto(friendshipRepository.save(friendship));
    }

    @Override
    public void rejectFriendship(Long userId, Long friendId) {
        var friendship = friendshipRepository
                .findByFriend1IdAndFriend2Id(userId, friendId).
                orElseThrow(() -> new NotFoundException("there is no such friendship entry"));

        if (friendship.getStatus().equals(FriendshipStatus.PENDING))
            friendshipRepository.delete(friendship);
        else
            throw new ConflictException("there is no pending request for friendship");
    }

    @Override
    public void revokeFriendship(Long userId, Long friendId) {
        var friendship = friendshipRepository
                .findByFriend1IdAndFriend2Id(userId, friendId)
                .orElseThrow(() -> new NotFoundException("there is no friendship entry"));

        if (friendship.getStatus().equals(FriendshipStatus.CONFIRMED))
            friendshipRepository.delete(friendship);
        else
            throw new ConflictException("there is no confirmed friendship");
    }

    @Override
    public List<FriendshipDto> findAllFriends(Long userId) {
        return friendshipRepository
                .findAllByFriend1Id(userId)
                .orElse(Collections.emptyList())
                .stream()
                .map(friendshipMapper::toDto)
                .collect(Collectors.toList());
    }
}
