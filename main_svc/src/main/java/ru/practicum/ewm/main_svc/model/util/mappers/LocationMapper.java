package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.LocationDto;
import ru.practicum.ewm.main_svc.model.dto.NewUserRequest;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.dto.UserShortDto;
import ru.practicum.ewm.main_svc.model.entity.Location;
import ru.practicum.ewm.main_svc.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationMapper {
    private final ModelMapper modelMapper;

    public Location locationDto2entity(LocationDto locationDto) {
        return Optional.ofNullable(locationDto).map(obj -> modelMapper.map(obj, Location.class)).orElse(null);
    }

    public LocationDto entity2userDto(Location location) {
        return Optional.ofNullable(location).map(obj -> modelMapper.map(obj, LocationDto.class)).orElse(null);
    }
}
