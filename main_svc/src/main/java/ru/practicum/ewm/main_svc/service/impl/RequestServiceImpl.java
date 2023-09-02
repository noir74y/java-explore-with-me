package ru.practicum.ewm.main_svc.service.impl;

import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.service.RequestService;

public class RequestServiceImpl implements RequestService {
    @Override
    public Iterable<ParticipationRequestDto> privateFindByUserAndEvent(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult privateUpdateStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest updateReq) {
        return null;
    }

    @Override
    public Iterable<ParticipationRequestDto> privateFindByUser(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto privateCreate(Long userId, Long eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto privateCancelStatus(Long userId, Long requestId) {
        return null;
    }
}
