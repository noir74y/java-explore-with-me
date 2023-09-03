package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.dto.UserShortDto;
import ru.practicum.ewm.main_svc.model.entity.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User newUserRequest2entity(NewUserRequest newUserRequest) {
        return Optional.ofNullable(newUserRequest).map(obj -> modelMapper.map(obj, User.class)).orElse(null);
    }

    public UserDto entity2userDto(User user) {
        return Optional.ofNullable(user).map(obj -> modelMapper.map(obj, UserDto.class)).orElse(null);
    }

    public UserShortDto entity2userShortDto(User user) {
        return Optional.ofNullable(user).map(obj -> modelMapper.map(obj, UserShortDto.class)).orElse(null);
    }
}
