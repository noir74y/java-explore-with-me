package ru.practicum.ewm.main_svc.error;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.main_svc.error.exception.KnownException;
import ru.practicum.ewm.main_svc.error.exception.EwmException;
import ru.practicum.ewm.main_svc.error.exception.UnknownException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ApiError> exceptionController(Exception initialException) {
        EwmException ewmException;

        log.error("{}", initialException.getMessage());

        if (initialException instanceof MethodArgumentNotValidException)
            ewmException = new KnownException("Incorrectly made request.", HttpStatus.BAD_REQUEST);
        else if (initialException instanceof PSQLException || initialException instanceof DataIntegrityViolationException)
            ewmException = new KnownException("Integrity constraint has been violated.", HttpStatus.CONFLICT);
        else if (initialException instanceof KnownException)
            ewmException = (KnownException) initialException;
        else
            ewmException = (UnknownException) initialException;

        return ewmException.getApiErrorMessage();
    }
}
