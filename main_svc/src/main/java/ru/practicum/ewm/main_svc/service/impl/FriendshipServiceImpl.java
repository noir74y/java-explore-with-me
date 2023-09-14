package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.model.dto.FriendshipDto;
import ru.practicum.ewm.main_svc.service.FriendshipService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipServiceImpl implements FriendshipService {
    @Override
    public FriendshipDto requestFriendship(Long userId, Long friendId) {
        return null;
    }

    @Override
    public FriendshipDto confirmFriendship(Long userId, Long friendId) {
        return null;
    }

    @Override
    public FriendshipDto rejectFriendship(Long userId, Long friendId) {
        return null;
    }

    @Override
    public void revokeFriendship(Long userId, Long friendId) {

    }

    @Override
    public List<FriendshipDto> findAllFriends(Long userId) {
        return null;
    }
}
