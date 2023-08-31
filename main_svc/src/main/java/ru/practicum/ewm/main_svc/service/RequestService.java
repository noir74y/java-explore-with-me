package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.PersonCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.PersonUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.req.RequestCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.RequestUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Person;
import ru.practicum.ewm.main_svc.model.entity.Request;

public interface RequestService {
    Request create(RequestCreateReq req);
    Request update(RequestUpdateReq req);
    void delete(Long id);
}
