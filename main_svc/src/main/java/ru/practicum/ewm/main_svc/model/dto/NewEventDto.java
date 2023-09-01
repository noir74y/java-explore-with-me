package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {
    @NotNull
    @Size(min = 2, max = 2000)
    String annotation;

    @NotNull
    Long category;

    @NotNull
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
    LocalDateTime eventDate;

    @NotNull
    LocationDto location;

    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;

    @NotNull
    @Size(min = 3, max = 120)
    String title;
}
