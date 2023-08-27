package ru.practicum.ewm.stat_svc.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.model.HitEntity;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HitModelMapper {
    private final ModelMapper modelMapper;

    public HitEntity hitIn2hitEntity(DtoHitIn hitIn) {
        return Optional.ofNullable(hitIn).map(obj -> modelMapper.map(obj, HitEntity.class)).orElse(null);
    }
}
