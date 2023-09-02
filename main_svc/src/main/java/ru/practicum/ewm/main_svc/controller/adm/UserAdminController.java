package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAdminController {
    UserService userService;

    @GetMapping
    public Iterable<UserDto> adminFindByIds(@RequestParam Iterable<Long> ids,
                                            @RequestParam(defaultValue = "0") Integer from,
                                            @RequestParam(defaultValue = "10") Integer size) {
        return userService.adminFindByIds(ids, from, size);
    }

    @PostMapping
    public UserDto adminCreate(@RequestBody @NotNull @Valid NewUserRequest newUserRequest) {
        return userService.adminCreate(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    public void adminDelete(@PathVariable @NotNull Long userId) {
        userService.adminDelete(userId);
    }
}
