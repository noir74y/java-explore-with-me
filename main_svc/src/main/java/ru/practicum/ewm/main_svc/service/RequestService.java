package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.RequestUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.resp.RequestResp;

public interface RequestService {
    Iterable<RequestResp> find(Long personId, Long eventId);

    RequestResp update(Long personId, Long eventId, RequestUpdateReq updateReq);
}
