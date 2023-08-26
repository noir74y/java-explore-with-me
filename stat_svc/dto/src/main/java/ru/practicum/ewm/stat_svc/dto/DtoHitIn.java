package ru.practicum.ewm.stat_svc.dto;

import lombok.*;

import javax.validation.constraints.*;
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
    @Size(max = 255)
    private String ip;
    @PastOrPresent
    private LocalDateTime timestamp;
}
