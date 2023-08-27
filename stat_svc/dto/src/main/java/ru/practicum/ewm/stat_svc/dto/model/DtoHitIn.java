package ru.practicum.ewm.stat_svc.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.stat_svc.dto.utils.AppConfig;
import ru.practicum.ewm.stat_svc.dto.utils.validations.HitIpConstraint;
import ru.practicum.ewm.stat_svc.dto.utils.validations.HitsRequestDatesConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoHitIn {
    @NotBlank
    @Size(max = 255)
    private String app;

    @NotBlank
    @Size(max = 255)
    private String uri;

    @NotBlank
    @HitIpConstraint
    private String ip;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConfig.DATE_TIME_FORMAT)
    @PastOrPresent
    private LocalDateTime timestamp;
}
