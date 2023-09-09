package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResp;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;

import java.util.List;

@Service
public interface RequestService {
    ParticipationRequestDto privateCreate(Long requestorId,
                                          Long eventId);

    ParticipationRequestDto privateCancelStatus(Long requestorId,
                                                Long requestId);

    List<ParticipationRequestDto> privateFindByRequestor(Long requestorId);

    List<ParticipationRequestDto> privateFindByInitiatorAndEvent(Long initiatorId,
                                                                 Long eventId);

    EventRequestStatusUpdateResp privateUpdateStatus(Long initiatorId,
                                                     Long eventId,
                                                     EventRequestStatusUpdateReq eventRequestStatusUpdateReq);
}
