package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.model.entity.Request;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final ModelMapper modelMapper;

    public ParticipationRequestDto entity2participationRequestDto(Request request) {
        return Optional.ofNullable(request).map(obj -> modelMapper.map(obj, ParticipationRequestDto.class)).orElse(null);
    }
}
