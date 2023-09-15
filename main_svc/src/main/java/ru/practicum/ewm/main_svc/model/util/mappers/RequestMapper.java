package ru.practicum.ewm.main_svc.model.util.mappers;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.model.entity.Request;

@Component
public class RequestMapper {
    public ParticipationRequestDto entity2participationRequestDto(Request request) {
        return ParticipationRequestDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .requester(request.getRequestor().getId())
                .created(request.getCreated())
                .status(request.getStatus().name())
                .build();
    }
}
