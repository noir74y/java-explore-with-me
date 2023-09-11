package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewCompilationDto {
    Set<Long> events;
    Boolean pinned = false;

    @NotBlank
    @Size(min = 1, max = 50)
    String title;
}
