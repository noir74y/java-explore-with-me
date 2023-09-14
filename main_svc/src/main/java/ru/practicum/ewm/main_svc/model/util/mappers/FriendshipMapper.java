package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.FriendshipDto;
import ru.practicum.ewm.main_svc.model.entity.Friendship;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipMapper {
    final ModelMapper modelMapper;

    public FriendshipDto toDto(Friendship friendship) {
        return Optional.ofNullable(friendship).map(obj -> modelMapper.map(obj, FriendshipDto.class)).orElse(null);
    }
}
