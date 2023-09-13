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
        log.error("{}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorMessage.builder()
                        .reason("Incorrectly made request.")
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .errors(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }

    @ExceptionHandler({ConflictException.class,
            PSQLException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<ApiErrorMessage> conflictExceptionHandler(Exception exception) {
        log.error("{}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiErrorMessage.builder()
                        .reason("Conflict is found")
                        .message(exception.getMessage())
                        .status(HttpStatus.CONFLICT.toString())
                        .errors(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorMessage> notFoundExceptionHandler(NotFoundException exception) {
        log.error("{}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiErrorMessage.builder()
                        .reason("Some data is not found")
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.toString())
                        .errors(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorMessage> otherExceptionHandler(Exception exception) {
        log.error("{}", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiErrorMessage.builder()
                        .reason("INTERNAL SERVER ERROR")
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.toString())
                        .errors(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                        .build());
    }
}
