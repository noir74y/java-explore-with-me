package ru.practicum.ewm.main_svc.controller.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResult;
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
    public ParticipationRequestDto privateCreate(@PathVariable @NotNull Long userId,
                                                 @PathVariable @NotNull Long eventId) {
        return requestService.privateCreate(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto privateCancelStatus(@PathVariable @NotNull Long userId,
                                                       @PathVariable @NotNull Long requestId) {
        return requestService.privateCancelStatus(userId, requestId);
    }

    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> privateFindByRequestor(@PathVariable @NotNull Long userId) {
        return requestService.privateFindByRequestor(userId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> privateFindByRequestorAndEvent(@PathVariable @NotNull Long userId,
                                                                        @PathVariable @NotNull Long eventId) {
        return requestService.privateFindByRequestorAndEvent(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult privateUpdateStatus(@PathVariable @NotNull Long userId,
                                                              @PathVariable @NotNull Long eventId,
                                                              @RequestBody @NotNull @Valid EventRequestStatusUpdateRequest updateReq) {
        return requestService.privateUpdateStatus(userId, eventId, updateReq);
    }
}
