package ru.practicum.ewm.main_svc.service.impl;

import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public Iterable<UserDto> adminFindByIds(Iterable<Long> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public UserDto adminCreate(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public void adminDelete(Long userId) {

    }
}
