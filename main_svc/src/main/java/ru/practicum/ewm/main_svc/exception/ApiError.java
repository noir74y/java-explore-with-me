package ru.practicum.ewm.main_svc.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError {
    final String status;
    final String reason;
    final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    final LocalDateTime timestamp = LocalDateTime.now();
}
