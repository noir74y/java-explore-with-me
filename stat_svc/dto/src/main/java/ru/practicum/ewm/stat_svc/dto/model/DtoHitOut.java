package ru.practicum.ewm.stat_svc.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoHitOut {
    private String app;
    private String uri;
    private Integer hits;
}