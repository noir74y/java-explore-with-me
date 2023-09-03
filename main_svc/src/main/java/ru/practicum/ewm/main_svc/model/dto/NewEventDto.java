package ru.practicum.ewm.main_svc.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.AppConfig;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

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
    @ValueOfEnumConstraint(enumClass = EventState.class)
    Long category;

    @NotNull
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_TIME_FORMAT)
    LocalDateTime eventDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_TIME_FORMAT)
    LocationDto location;

    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;

    @NotNull
    @Size(min = 3, max = 120)
    String title;
}
