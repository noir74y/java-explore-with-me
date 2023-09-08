package ru.practicum.ewm.main_svc.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    @JsonAlias("category")
    Long catId;

    @NotNull
    @Size(min = 20, max = 7000)
    String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MainAppConfig.DATE_TIME_FORMAT)
    @Future
    LocalDateTime eventDate;

    @NotNull
    @Valid
    @JsonAlias("location")
    LocationDto locationDto;

    Boolean paid = false;

    @PositiveOrZero
    Integer participantLimit = 0;

    Boolean requestModeration = true;

    @NotNull
    @Size(min = 3, max = 120)
    String title;
}
