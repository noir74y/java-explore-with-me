package ru.practicum.ewm.main_svc.exception;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ApiError> exceptionController(Exception exception) {
        log.error("{}", exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiError.builder()
                            .status(HttpStatus.BAD_REQUEST.toString())
                            .message(exception.getMessage())
                            .reason("Incorrectly made request.").build());
        else if (exception instanceof PSQLException || exception instanceof DataIntegrityViolationException)
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiError.builder()
                            .status(HttpStatus.CONFLICT.toString())
                            .message(exception.getMessage())
                            .reason("Integrity constraint has been violated.").build());


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .message(exception.getMessage())
                        .reason("other error").build());
    }
}
