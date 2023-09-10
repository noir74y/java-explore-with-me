package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResp;
import ru.practicum.ewm.main_svc.model.dto.ParticipationRequestDto;
import ru.practicum.ewm.main_svc.model.entity.Request;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;
import ru.practicum.ewm.main_svc.model.util.mappers.RequestMapper;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.repository.RequestRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;
import ru.practicum.ewm.main_svc.service.RequestService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestServiceImpl implements RequestService {
    final RequestRepository requestRepository;
    final RequestMapper requestMapper;
    final UserRepository userRepository;
    final EventRepository eventRepository;

    @Override
    @Transactional
    public ParticipationRequestDto privateCreateRequest(Long requestorId, Long eventId) {
        var requestor = userRepository
                .findById(requestorId)
                .orElseThrow(() -> new MainEwmException(String.format("there is no requestor with user_id=%d", requestorId), HttpStatus.NOT_FOUND));

        var event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d", eventId), HttpStatus.NOT_FOUND));

        if (requestRepository.existsByRequestorIdAndEventIdAndStatus(requestorId, eventId, RequestStatus.PENDING))
            throw new MainEwmException("such pending request is already exists", HttpStatus.CONFLICT);

        if (Objects.equals(event.getInitiator().getId(), requestorId))
            throw new MainEwmException("requestor is an initiator of the event", HttpStatus.CONFLICT);

        if (!event.getState().equals(EventState.PUBLISHED.name()))
            throw new MainEwmException("the event is not published yet", HttpStatus.CONFLICT);

        if (event.getParticipantLimit() != 0 && requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED) == event.getParticipantLimit())
            throw new MainEwmException("there are too many participants already", HttpStatus.CONFLICT);

        return requestMapper.entity2participationRequestDto(requestRepository.save(Request.builder()
                .created(LocalDateTime.now())
                .requestor(requestor)
                .event(event)
                .status(event.getParticipantLimit() == 0 || !event.getRequestModeration() ? RequestStatus.CONFIRMED : RequestStatus.PENDING).build()));
    }

    @Override
    @Transactional
    public ParticipationRequestDto privateCancelRequestStatus(Long requestorId, Long requestId) {
        var request = requestRepository
                .findByIdAndRequestorIdAndStatusIn(requestId, requestorId, List.of(RequestStatus.PENDING, RequestStatus.CONFIRMED))
                .orElseThrow(() -> new MainEwmException("there is no such your request in a proper status", HttpStatus.NOT_FOUND));

        request.setStatus(RequestStatus.CANCELED);
        return requestMapper.entity2participationRequestDto(requestRepository.save(request));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> privateFindRequestsByRequestor(Long requestorId) {
        if (!userRepository.existsById(requestorId))
            throw new MainEwmException(String.format("there is no requestor with user_id=%d", requestorId), HttpStatus.NOT_FOUND);

        return requestRepository
                .findAllByRequestorId(requestorId)
                .orElse(Collections.emptyList())
                .stream()
                .map(requestMapper::entity2participationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> privateFindRequestsByInitiatorAndEvent(Long initiatorId, Long eventId) {
        return requestRepository
                .findAllByInitiatorIdAndEventId(initiatorId, eventId)
                .orElse(Collections.emptyList())
                .stream()
                .map(requestMapper::entity2participationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public synchronized EventRequestStatusUpdateResp privateUpdateRequestStatus(Long initiatorId, Long eventId, EventRequestStatusUpdateReq eventRequestStatusUpdateReq) {
        if (!userRepository.existsById(initiatorId))
            throw new MainEwmException(String.format("there is no user with id=%d", initiatorId), HttpStatus.NOT_FOUND);

        var event = Optional.ofNullable(eventRepository
                        .findByInitiatorIdAndId(initiatorId, eventId))
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d and initiator_id=%d", eventId, initiatorId), HttpStatus.NOT_FOUND));

        if (!event.getState().equals(EventState.PUBLISHED.name()))
            throw new MainEwmException("the event is not published yet", HttpStatus.CONFLICT);

        // get all pending requests according to eventRequestStatusUpdateReq.requestsIdList, otherwise get all pending request for the event
        List<Request> requests = !Objects.isNull(eventRequestStatusUpdateReq.getRequestsIdList()) && !eventRequestStatusUpdateReq.getRequestsIdList().isEmpty()
                ? requestRepository.findAllByStatusAndIdIn(RequestStatus.PENDING, eventRequestStatusUpdateReq.getRequestsIdList())
                : requestRepository.findAllByStatusAndEventId(RequestStatus.PENDING, eventId);

        Optional.ofNullable(requests).orElseThrow(() -> new MainEwmException("requests aren't found", HttpStatus.NOT_FOUND));

        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
        var o = new Object() {
            long currentParticipantsNumber;
            Boolean isLimitReached;
        };

        if (eventRequestStatusUpdateReq.getStatus().equals(RequestStatus.REJECTED.name()))
            rejectedRequests
                    .addAll(requests.stream().peek(request -> {
                        request.setStatus(RequestStatus.REJECTED);
                    }).map(requestMapper::entity2participationRequestDto).collect(Collectors.toList()));
        else if (eventRequestStatusUpdateReq.getStatus().equals(RequestStatus.CONFIRMED.name()) &&
                (event.getParticipantLimit() == 0 || !event.getRequestModeration()))
            confirmedRequests
                    .addAll(requests.stream().peek(request -> {
                        request.setStatus(RequestStatus.CONFIRMED);
                    }).map(requestMapper::entity2participationRequestDto).collect(Collectors.toList()));
        else {
            o.currentParticipantsNumber = requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED);
            requests.forEach(request -> {
                if (event.getParticipantLimit() < o.currentParticipantsNumber) {
                    request.setStatus(RequestStatus.CONFIRMED);
                    o.isLimitReached = ++o.currentParticipantsNumber == event.getParticipantLimit() ? true : null;
                    confirmedRequests.add(requestMapper.entity2participationRequestDto(request));
                } else {
                    request.setStatus(RequestStatus.REJECTED);
                    rejectedRequests.add(requestMapper.entity2participationRequestDto(request));
                }
            });
        }

        requestRepository.saveAll(requests);

        Optional.ofNullable(o.isLimitReached).ifPresent(obj -> {
            throw new MainEwmException("limit of participants has been reached", HttpStatus.CONFLICT);
        });

        return EventRequestStatusUpdateResp.builder()
                .confirmedRequests(confirmedRequests)
                .rejectedRequests(rejectedRequests).build();
    }
}
