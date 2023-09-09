package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
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
public class RequestServiceImpl implements RequestService {
    RequestRepository requestRepository;
    RequestMapper requestMapper;
    UserRepository userRepository;
    EventRepository eventRepository;

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
                .createdOn(LocalDateTime.now())
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
    public EventRequestStatusUpdateResp privateUpdateRequestStatus(Long initiatorId, Long eventId, EventRequestStatusUpdateReq eventRequestStatusUpdateReq) {
        if (!userRepository.existsById(initiatorId))
            throw new MainEwmException(String.format("there is no user with id=%d", initiatorId), HttpStatus.NOT_FOUND);

        var event = Optional.ofNullable(eventRepository
                        .findByInitiatorIdAndId(initiatorId, eventId))
                .orElseThrow(() -> new MainEwmException(String.format("there is no event with id=%d and initiator_id=%d", eventId, initiatorId), HttpStatus.NOT_FOUND));

        if (!event.getState().equals(EventState.PUBLISHED.name()))
            throw new MainEwmException("the event is not published yet", HttpStatus.CONFLICT);

        var o = new Object() {
            long currentParticipantsNumber = requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED);
            Boolean isLimitReached;
        };

        List<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestDto> rejectedRequests = new ArrayList<>();

        List<Request> requests = requestRepository
                .findAllByStatusAndIdIn(RequestStatus.PENDING, eventRequestStatusUpdateReq.getRequestsIdList())
                .orElse(Collections.emptyList())
                .stream()
                .peek(request -> {
                    if (event.getParticipantLimit() == 0 || !event.getRequestModeration())
                        request.setStatus(RequestStatus.CONFIRMED);
                    else if (event.getParticipantLimit() < o.currentParticipantsNumber) {
                        request.setStatus(RequestStatus.CONFIRMED);
                        o.isLimitReached = ++o.currentParticipantsNumber == event.getParticipantLimit() ? true : null;
                        confirmedRequests.add(requestMapper.entity2participationRequestDto(request));
                    } else {
                        request.setStatus(RequestStatus.REJECTED);
                        rejectedRequests.add(requestMapper.entity2participationRequestDto(request));
                    }
                })
                .collect(Collectors.toList());

        requestRepository.saveAll(requests);

        if (o.isLimitReached)
            throw new MainEwmException("limit is reached", HttpStatus.CONFLICT);

        return EventRequestStatusUpdateResp.builder()
                .confirmedRequests(confirmedRequests)
                .rejectedRequests(rejectedRequests).build();
    }
}
