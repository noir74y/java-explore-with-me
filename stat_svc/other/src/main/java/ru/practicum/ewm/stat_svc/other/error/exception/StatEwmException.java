package ru.practicum.ewm.stat_svc.other.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.practicum.ewm.stat_svc.other.error.StatErrorMessage;


public class StatEwmException extends RuntimeException {
    @Getter
    protected HttpStatus httpErrorStatus;
    protected String cause;
    protected String message;

    public StatErrorMessage prepareErrorMessage() {
        return new StatErrorMessage(cause, message);
    }

}
