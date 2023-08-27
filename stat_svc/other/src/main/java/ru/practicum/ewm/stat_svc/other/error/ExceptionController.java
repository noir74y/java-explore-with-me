package ru.practicum.ewm.stat_svc.other.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.stat_svc.other.error.exception.EwmException;
import ru.practicum.ewm.stat_svc.other.error.exception.OtherException;
import ru.practicum.ewm.stat_svc.other.error.exception.CustomValidationException;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionController(Exception exception) {
        EwmException ewmException;

        log.error("{}", exception.getMessage());

        if (exception instanceof DateTimeParseException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(exception.getMessage(), "incorrect format for LocalDateTime"));
        else if (exception instanceof HttpMessageNotReadableException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(exception.getMessage(), "InvalidFormatException exception"));
        else if (exception instanceof MethodArgumentNotValidException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessage(exception.getMessage(), "validation exception"));
        else if (exception instanceof CustomValidationException)
            ewmException = (CustomValidationException) exception;
        else
            ewmException = new OtherException(exception);

        return ResponseEntity.status(ewmException.getHttpErrorStatus()).body(ewmException.prepareErrorMessage());
    }
}
