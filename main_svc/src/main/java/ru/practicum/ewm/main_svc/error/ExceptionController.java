package ru.practicum.ewm.main_svc.error;

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
        EwmException ewmException;

        log.error("{}", exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException)
            ewmException = new EwmException("Incorrectly made request.", HttpStatus.BAD_REQUEST);
        else if (exception instanceof PSQLException || exception instanceof DataIntegrityViolationException)
            ewmException = new EwmException("Integrity constraint has been violated.", HttpStatus.CONFLICT);
        else if (exception instanceof EwmException)
            ewmException = (EwmException) exception;
        else
            ewmException = new EwmException("UnknownException", HttpStatus.INTERNAL_SERVER_ERROR);

        return ewmException.getApiErrorMessage();
    }
}
