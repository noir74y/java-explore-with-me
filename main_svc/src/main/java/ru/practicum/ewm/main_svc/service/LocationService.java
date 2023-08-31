package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.LocationCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.LocationUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Location;

public interface LocationService {
    Location create(LocationCreateReq req);

    Location update(LocationUpdateReq req);

    void delete(Long id);
}
