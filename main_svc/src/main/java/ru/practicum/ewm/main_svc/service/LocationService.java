package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.LocationCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.LocationUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.req.PersonCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.PersonUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Location;
import ru.practicum.ewm.main_svc.model.entity.Person;

public interface LocationService {
    Location create(LocationCreateReq req);
    Location update(LocationUpdateReq req);
    void delete(Long id);
}
