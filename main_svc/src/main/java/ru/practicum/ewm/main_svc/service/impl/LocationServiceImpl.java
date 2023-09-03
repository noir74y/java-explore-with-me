package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.LocationDto;
import ru.practicum.ewm.main_svc.service.LocationService;
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Override
    public LocationDto create(LocationDto locationDto) {
        return null;
    }
}
