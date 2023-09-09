package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.main_svc.model.dto.EventRequestStatusUpdateResult;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    RequestRepository requestRepository;
    RequestMapper requestMapper;
    UserRepository userRepository;
    EventRepository eventRepository;

    @Override
    public ParticipationRequestDto privateCreate(Long requestorId, Long eventId) {
        var requestor = userRepository
                .findById(requestorId)
                .orElseThrow(() -> new MainEwmException(String.format("there is no requestor with user_id=%d", requestorId), HttpStatus.NOT_FOUND));

        var event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new MainEwmException("there is no event with id=%d", HttpStatus.NOT_FOUND));

        if (requestRepository.existsByRequestorIdAndEventIdAndStatus(requestorId, eventId, RequestStatus.PENDING))
            throw new MainEwmException("such pending request is already exists", HttpStatus.CONFLICT);

        if (Objects.equals(event.getInitiator().getId(), requestorId))
            throw new MainEwmException("requestor is an initiator of the event", HttpStatus.CONFLICT);

        if (!event.getState().equals(EventState.PUBLISHED.name()))
            throw new MainEwmException("the event is not published yet", HttpStatus.CONFLICT);

        if (requestRepository.countByEventIdAndStatus(eventId, RequestStatus.CONFIRMED) == event.getParticipantLimit())
            throw new MainEwmException("there are too many participants already", HttpStatus.CONFLICT);

        return requestMapper.entity2participationRequestDto(requestRepository.save(Request.builder()
                .createdOn(LocalDateTime.now())
                .requestor(requestor)
                .event(event)
                .status(event.getRequestModeration() ? RequestStatus.PENDING : RequestStatus.CONFIRMED).build()));
    }

    @Override
    public ParticipationRequestDto privateCancelStatus(Long requestorId, Long requestId) {
        var request = requestRepository
                .findByIdAndRequestorIdAndStatusIn(requestId, requestorId, List.of(RequestStatus.PENDING, RequestStatus.CONFIRMED))
                .orElseThrow(() -> new MainEwmException("there is no such your request in a proper status", HttpStatus.NOT_FOUND));

        request.setStatus(RequestStatus.CANCELED);
        return requestMapper.entity2participationRequestDto(requestRepository.save(request));
    }

    @Override
    public List<ParticipationRequestDto> privateFindByRequestor(Long requestorId) {
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
    public List<ParticipationRequestDto> privateFindByRequestorAndEvent(Long requestorId, Long eventId) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult privateUpdateStatus(Long requestorId, Long eventId, EventRequestStatusUpdateRequest updateReq) {
        return null;
    }
}
