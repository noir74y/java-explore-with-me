package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResp;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;

import java.util.List;

@Service
public interface RequestService {
    ParticipationRequestDto privateCreateRequest(Long requestorId,
                                                 Long eventId);

    ParticipationRequestDto privateCancelRequestStatus(Long requestorId,
                                                       Long requestId);

    List<ParticipationRequestDto> privateFindRequestsByRequestor(Long requestorId);

    List<ParticipationRequestDto> privateFindRequestsByInitiatorAndEvent(Long initiatorId,
                                                                         Long eventId);

    EventRequestStatusUpdateResp privateUpdateRequestStatus(Long initiatorId,
                                                            Long eventId,
                                                            EventRequestStatusUpdateReq eventRequestStatusUpdateReq);
}
