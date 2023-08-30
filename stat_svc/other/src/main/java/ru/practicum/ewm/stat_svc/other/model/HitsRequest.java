package ru.practicum.ewm.stat_svc.other.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.stat_svc.other.validations.HitsRequestDatesConstraint;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@HitsRequestDatesConstraint
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitsRequest {
    LocalDateTime start;
    LocalDateTime end;
    List<String> uris;
    Boolean unique;
}
