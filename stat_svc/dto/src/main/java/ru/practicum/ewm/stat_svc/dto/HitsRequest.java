package ru.practicum.ewm.stat_svc.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.stat_svc.dto.validations.HitsRequestDatesConstraint;

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
