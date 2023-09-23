package ru.practicum.ewm.main_svc.error;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({BadRequestException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity<ApiErrorMessage> badRequestExceptionHandler(Exception exception) {
        return getResponseEntity(exception, HttpStatus.BAD_REQUEST, "Incorrectly made request.");
    }

    @ExceptionHandler({ConflictException.class,
            PSQLException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<ApiErrorMessage> conflictExceptionHandler(Exception exception) {
        return getResponseEntity(exception, HttpStatus.CONFLICT, "Conflict is found.");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorMessage> notFoundExceptionHandler(NotFoundException exception) {
        return getResponseEntity(exception, HttpStatus.NOT_FOUND, "Some data is not found.");
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorMessage> otherExceptionHandler(Exception exception) {
        return getResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
    }

    private ResponseEntity<ApiErrorMessage> getResponseEntity(Exception exception, HttpStatus httpStatus, String reason) {
        log.error("{}", exception.getMessage());
        return ResponseEntity
                .status(httpStatus)
                .body(ApiErrorMessage.builder()
                        .reason(reason)
                        .message(exception.getMessage())
                        .status(httpStatus.toString())
                        .errors(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }
}
