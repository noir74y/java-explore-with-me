package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.FriendshipDto;

import java.util.List;

public interface FriendshipService {

    FriendshipDto requestFriendship(Long userId, Long friendId);

    FriendshipDto confirmFriendship(Long userId, Long friendId);

    void rejectFriendship(Long userId, Long friendId);

    void revokeFriendship(Long userId, Long friendId);

    List<FriendshipDto> findAllFriends(Long userId);
}
