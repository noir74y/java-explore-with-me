package ru.practicum.ewm.stat_svc.other.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.stat_svc.other.util.StatAppConfig;
import ru.practicum.ewm.stat_svc.other.validations.HitIpConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoHitIn {
    @NotBlank
    @Size(max = 255)
    String app;

    @NotBlank
    @Size(max = 255)
    String uri;

    @NotBlank
    @HitIpConstraint
    String ip;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StatAppConfig.DATE_TIME_FORMAT)
    @PastOrPresent
    LocalDateTime timestamp;
}
