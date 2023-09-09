package ru.practicum.ewm.main_svc.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainErrorMessage {
    final String reason;
    final String message;
    final String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = MainAppConfig.DATE_TIME_FORMAT)
    final LocalDateTime timestamp = LocalDateTime.now();
    final List<String> errors;
}
