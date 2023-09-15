package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> adminFindUsersByIds(List<Long> ids,
                                      Integer from,
                                      Integer size);

    UserDto adminCreateUser(NewUserRequest newUserRequest);

    void adminDeleteUser(Long userId);
}
