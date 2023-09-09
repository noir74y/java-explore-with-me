package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.util.mappers.UserMapper;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> adminFindUsersByIds(List<Long> ids, Integer from, Integer size) {
        return userMapper.bulkEntity2userShortDto(userRepository.findByIdIn(ids, PageRequest.of(from / size, size)));
    }

    @Override
    @Transactional
    public UserDto adminCreateUser(NewUserRequest newUserRequest) {
        return userMapper.entity2userDto(userRepository.save(userMapper.newUserRequest2entity(newUserRequest)));
    }

    @Override
    @Transactional
    public void adminDeleteUser(Long userId) {
        if (userRepository.existsById(userId)) userRepository.deleteById(userId);
        else throw new MainEwmException(String.format("User with id=%d was not found.", userId), HttpStatus.NOT_FOUND);
    }
}
