package ru.practicum.ewm.main_svc.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.ewm.main_svc.error.ApiError;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EwmException extends RuntimeException {
    String reason;
    HttpStatus status;

    public ResponseEntity<ApiError> getApiErrorMessage() {
        return ResponseEntity
                .status(status)
                .body(ApiError.builder()
                        .errors(Arrays.stream(this.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .message(this.getMessage())
                        .reason(reason)
                        .status(status.toString())
                        .build());
    }
}
