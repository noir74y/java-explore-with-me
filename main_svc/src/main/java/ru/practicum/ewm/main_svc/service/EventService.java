package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.EventCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.EventUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.resp.EventFullResp;
import ru.practicum.ewm.main_svc.model.dto.resp.EventShortResp;
import ru.practicum.ewm.main_svc.model.entity.Event;

import java.util.List;

public interface EventService {
 Iterable<Event> findByPerson(Long personId, Integer from, Integer size);

 EventFullResp create(Long personId, EventCreateReq createReq);

 EventFullResp find(Long personId, Long eventId);

 EventShortResp update(Long personId, Long eventId, EventUpdateReq updateReq);
}
