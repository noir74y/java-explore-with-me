package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.LocationDto;
import ru.practicum.ewm.main_svc.model.entity.Location;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationMapper {
    final ModelMapper modelMapper;

    public Location locationDto2entity(LocationDto locationDto) {
        return Optional.ofNullable(locationDto).map(obj -> modelMapper.map(obj, Location.class)).orElse(null);
    }
}
