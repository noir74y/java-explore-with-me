package ru.practicum.ewm.main_svc.controller.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResp;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPrivateController {
    final RequestService requestService;

    @PostMapping("/requests")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ParticipationRequestDto privateCreateRequest(@PathVariable("userId") @NotNull Long requestorId,
                                                        @RequestParam @NotNull Long eventId) {
        log.info("POST /users/{}/requests eventId={}", requestorId, eventId);
        return requestService.privateCreateRequest(requestorId, eventId);
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ParticipationRequestDto privateCancelRequestStatus(@PathVariable("userId") @NotNull Long requestorId,
                                                              @PathVariable @NotNull Long requestId) {
        log.info("PATCH /users/{}/requests/{}/cancel", requestorId, requestId);
        return requestService.privateCancelRequestStatus(requestorId, requestId);
    }

    @GetMapping("/requests")
    public List<ParticipationRequestDto> privateFindRequestsByRequestor(@PathVariable("userId") @NotNull Long requestorId) {
        log.info("GET /users/{}/requests", requestorId);
        return requestService.privateFindRequestsByRequestor(requestorId);
    }

    @GetMapping("/events/{eventId}/requests")
    public List<ParticipationRequestDto> privateFindRequestsByInitiatorAndEvent(@PathVariable("userId") @NotNull Long initiatorId,
                                                                                @PathVariable @NotNull Long eventId) {
        log.info("GET /users/{}/events/{}/requests", initiatorId, eventId);
        return requestService.privateFindRequestsByInitiatorAndEvent(initiatorId, eventId);
    }

    @PatchMapping("/events/{eventId}/requests")
    public EventRequestStatusUpdateResp privateUpdateRequestStatus(@PathVariable("userId") @NotNull Long initiatorId,
                                                                   @PathVariable @NotNull Long eventId,
                                                                   @RequestBody EventRequestStatusUpdateReq eventRequestStatusUpdateReq) {
        log.info("PATCH /users/{}/events/{}/requests {}", initiatorId, eventId, eventRequestStatusUpdateReq);
        return requestService.privateUpdateRequestStatus(initiatorId, eventId, eventRequestStatusUpdateReq);
    }
}
