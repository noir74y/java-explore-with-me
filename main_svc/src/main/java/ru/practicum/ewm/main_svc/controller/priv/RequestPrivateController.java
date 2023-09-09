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
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPrivateController {
    RequestService requestService;

    @PostMapping("/{userId}/requests")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ParticipationRequestDto privateCreate(@PathVariable("userId") @NotNull Long requestorId,
                                                 @PathVariable @NotNull Long eventId) {
        return requestService.privateCreate(requestorId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto privateCancelStatus(@PathVariable("userId") @NotNull Long requestorId,
                                                       @PathVariable @NotNull Long requestId) {
        return requestService.privateCancelStatus(requestorId, requestId);
    }

    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> privateFindByRequestor(@PathVariable("userId") @NotNull Long requestorId) {
        return requestService.privateFindByRequestor(requestorId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> privateFindByInitiatorAndEvent(@PathVariable("userId") @NotNull Long initiatorId,
                                                                        @PathVariable @NotNull Long eventId) {
        return requestService.privateFindByInitiatorAndEvent(initiatorId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResp privateUpdateStatus(@PathVariable("userId") @NotNull Long initiatorId,
                                                            @PathVariable @NotNull Long eventId,
                                                            @RequestBody @NotNull @Valid EventRequestStatusUpdateReq eventRequestStatusUpdateReq) {
        return requestService.privateUpdateStatus(initiatorId, eventId, eventRequestStatusUpdateReq);
    }
}
