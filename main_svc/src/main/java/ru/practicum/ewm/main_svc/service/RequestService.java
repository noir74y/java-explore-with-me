package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
@Service
public interface RequestService {
    Iterable<ParticipationRequestDto> privateFindByUserAndEvent(Long userId, Long eventId);

    EventRequestStatusUpdateResult privateUpdateStatus(Long userId, Iterable<Long> events, EventRequestStatusUpdateRequest updateReq);

    Iterable<ParticipationRequestDto> privateFindByUser(Long userId);

    ParticipationRequestDto privateCreate(Long userId, Long eventId);

    ParticipationRequestDto privateCancelStatus(Long userId, Long requestId);
}
