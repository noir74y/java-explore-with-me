package ru.practicum.ewm.stat_svc.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoHitOut {
    String app;
    String uri;
    Long hits;
}
