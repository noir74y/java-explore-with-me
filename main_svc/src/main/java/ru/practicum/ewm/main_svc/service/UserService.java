package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;

public interface UserService {
    Iterable<UserDto> adminFindByIds(Iterable<Long> ids, Integer from, Integer size);

    UserDto adminCreate(NewUserRequest newUserRequest);

    void adminDelete(Long userId);
}
