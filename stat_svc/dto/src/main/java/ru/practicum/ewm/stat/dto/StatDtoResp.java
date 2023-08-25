package ru.practicum.ewm.stat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatDtoResp {
    private String app;
    private String uri;
    private Integer hits;
}
