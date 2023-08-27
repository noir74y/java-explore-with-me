package ru.practicum.ewm.stat_svc.dto.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.stat_svc.dto.utils.validations.HitsRequestDatesConstraint;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@HitsRequestDatesConstraint
public class HitsRequest {
    private LocalDateTime start;
    private LocalDateTime end;
    private List<String> uris;
    private Boolean unique;
}
