package ru.practicum.ewm.stat_svc.other.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.stat_svc.other.error.exception.CustomValidationException;
import ru.practicum.ewm.stat_svc.other.error.exception.StatEwmException;
import ru.practicum.ewm.stat_svc.other.error.exception.OtherException;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class StatExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatErrorMessage> exceptionController(Exception exception) {
        StatEwmException statEwmException;

        log.error("{}", exception.getMessage());

        if (exception instanceof DateTimeParseException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StatErrorMessage(exception.getMessage(), "incorrect format for LocalDateTime"));
        else if (exception instanceof HttpMessageNotReadableException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StatErrorMessage(exception.getMessage(), "InvalidFormatException exception"));
        else if (exception instanceof MethodArgumentNotValidException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StatErrorMessage(exception.getMessage(), "validation exception"));
        else if (exception instanceof CustomValidationException)
            statEwmException = (CustomValidationException) exception;
        else
            statEwmException = new OtherException(exception);

        return ResponseEntity.status(statEwmException.getHttpErrorStatus()).body(statEwmException.prepareErrorMessage());
    }
}
