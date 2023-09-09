package ru.practicum.ewm.main_svc.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainEwmException extends RuntimeException {
    String reason;
    HttpStatus httpStatus;

    public ResponseEntity<MainErrorMessage> getApiErrorMessage() {
        return ResponseEntity
                .status(httpStatus)
                .body(MainErrorMessage.builder()
                        .reason(reason)
                        .message(this.getMessage())
                        .status(httpStatus.toString())
                        .errors(Arrays.stream(this.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }

    public MainEwmException(Exception exception, HttpStatus httpStatus) {
        this.reason = exception.toString();
        this.httpStatus = httpStatus;
    }
}
