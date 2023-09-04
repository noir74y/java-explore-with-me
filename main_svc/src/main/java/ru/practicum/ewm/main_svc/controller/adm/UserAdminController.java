package ru.practicum.ewm.main_svc.controller.adm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.util.AppConfig;
import ru.practicum.ewm.main_svc.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> adminFindByIds(@RequestParam List<Long> ids,
                                        @RequestParam(defaultValue = AppConfig.FROM) Integer from,
                                        @RequestParam(defaultValue = AppConfig.SIZE) Integer size) {
        log.info("GET /admin/users {}, {}, {}", ids, from, size);
        return userService.adminFindByIds(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto adminCreate(@RequestBody @NotNull @Valid NewUserRequest newUserRequest) {
        log.info("POST /admin/users {}", newUserRequest);
        return userService.adminCreate(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void adminDelete(@PathVariable @NotNull Long userId) {
        log.info("DELETE /admin/{}", userId);
        userService.adminDelete(userId);
    }
}
