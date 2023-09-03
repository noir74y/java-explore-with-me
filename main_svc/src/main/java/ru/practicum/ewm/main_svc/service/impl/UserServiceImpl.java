package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.util.mappers.UserMapper;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Iterable<UserDto> adminFindByIds(Iterable<Long> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public UserDto adminCreate(NewUserRequest newUserRequest) {
        return userMapper.entity2userDto(userRepository.save(userMapper.newUserRequest2entity(newUserRequest)));
    }

    @Override
    public void adminDelete(Long userId) {
        userRepository.deleteById(userId);
    }
}
