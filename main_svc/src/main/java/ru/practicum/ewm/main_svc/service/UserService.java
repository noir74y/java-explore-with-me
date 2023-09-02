package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
@Service
public interface UserService {
    Iterable<UserDto> adminFindByIds(Iterable<Long> ids, Integer from, Integer size);

    UserDto adminCreate(NewUserRequest newUserRequest);

    void adminDelete(Long userId);
}
