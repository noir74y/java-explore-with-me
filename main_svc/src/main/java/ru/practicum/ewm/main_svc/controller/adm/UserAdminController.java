package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.service.UserService;

@Slf4j
@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAdminController {
    UserService userService;

    public Iterable<UserDto> adminFindByIds(Iterable<Long> ids, Integer from, Integer size) {
        return userService.adminFindByIds(ids, from, size);
    }

    public UserDto adminCreate(NewUserRequest newUserRequest) {
        return userService.adminCreate(newUserRequest);
    }

    public void adminDelete(Long userId) {
        userService.adminDelete(userId);
    }
}
