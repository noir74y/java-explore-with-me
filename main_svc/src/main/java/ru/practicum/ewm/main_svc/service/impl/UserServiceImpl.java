package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.NotFoundException;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.util.mappers.UserMapper;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> adminFindUsersByIds(List<Long> ids, Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size);
        return userMapper.bulkEntity2userShortDto(
                Objects.isNull(ids)
                        ? userRepository.findAll(pageRequest).getContent()
                        : userRepository.findByIdIn(ids, pageRequest)
        );
    }

    @Override
    public UserDto adminCreateUser(NewUserRequest newUserRequest) {
        return userMapper.entity2userDto(userRepository.save(userMapper.newUserRequest2entity(newUserRequest)));
    }

    @Override
    public void adminDeleteUser(Long userId) {
        if (userRepository.existsById(userId)) userRepository.deleteById(userId);
        else throw new NotFoundException(String.format("User with id=%d was not found.", userId));
    }
}
