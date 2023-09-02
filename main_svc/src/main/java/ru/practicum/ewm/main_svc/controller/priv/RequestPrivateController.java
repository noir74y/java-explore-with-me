package ru.practicum.ewm.main_svc.controller.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.service.RequestService;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPrivateController {
    RequestService requestService;

    public Iterable<ParticipationRequestDto> privateFindByUserAndEvent(Long userId, Long eventId) {
        return requestService.privateFindByUserAndEvent(userId, eventId);
    }

    public EventRequestStatusUpdateResult privateUpdateStatus(Long userId, Iterable<Long> events, EventRequestStatusUpdateRequest updateReq) {
        return requestService.privateUpdateStatus(userId, events, updateReq);
    }

    public Iterable<ParticipationRequestDto> privateFindByUser(Long userId) {
        return requestService.privateFindByUser(userId);
    }

    public ParticipationRequestDto privateCreate(Long userId, Long eventId) {
        return requestService.privateCreate(userId, eventId);
    }

    public ParticipationRequestDto privateCancelStatus(Long userId, Long requestId) {
        return requestService.privateCancelStatus(userId, requestId);
    }
}
