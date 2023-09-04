package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> adminFindByIds(List<Long> ids,
                                 Integer from,
                                 Integer size);

    UserDto adminCreate(NewUserRequest newUserRequest);

    void adminDelete(Long userId);
}
