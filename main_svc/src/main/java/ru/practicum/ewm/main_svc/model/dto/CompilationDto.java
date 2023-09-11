package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationDto {
    List<EventShortDto> events;
    Long id;
    Boolean pinned;
    String title;
}
