package ru.practicum.ewm.stat_svc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatDtoReq {
    private String app;
    private String uri;
    private String ip;
    private LocalDateTime timestamp;
}
