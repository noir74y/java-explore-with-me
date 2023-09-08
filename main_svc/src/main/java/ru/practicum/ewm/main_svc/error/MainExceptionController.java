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
public class MainExceptionController {

    @ExceptionHandler
    public ResponseEntity<MainErrorMessage> exceptionController(Exception exception) {
        MainEwmException mainEwmException;

        log.error("{}", exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException)
            mainEwmException = new MainEwmException("Incorrectly made request.", HttpStatus.BAD_REQUEST);
        else if (exception instanceof PSQLException || exception instanceof DataIntegrityViolationException)
            mainEwmException = new MainEwmException("Integrity constraint has been violated.", HttpStatus.CONFLICT);
        else if (exception instanceof MainEwmException)
            mainEwmException = (MainEwmException) exception;
        else
            mainEwmException = new MainEwmException("UnknownException", HttpStatus.INTERNAL_SERVER_ERROR);

        return mainEwmException.getApiErrorMessage();
    }
}
