package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.LocationDto;

@Service
public interface LocationService {
    LocationDto create(LocationDto locationDto);
}
