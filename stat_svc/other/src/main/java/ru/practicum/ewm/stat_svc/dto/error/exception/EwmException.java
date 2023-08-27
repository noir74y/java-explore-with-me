package ru.practicum.ewm.stat_svc.dto.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.practicum.ewm.stat_svc.dto.error.ErrorMessage;


public class EwmException extends RuntimeException {
    @Getter
    protected HttpStatus httpErrorStatus;
    protected String cause;
    protected String message;

    public ErrorMessage prepareErrorMessage() {
        return new ErrorMessage(cause, message);
    }

}
