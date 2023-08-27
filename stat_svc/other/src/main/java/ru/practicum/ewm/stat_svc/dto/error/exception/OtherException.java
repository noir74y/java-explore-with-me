package ru.practicum.ewm.stat_svc.dto.error.exception;

import org.springframework.http.HttpStatus;
import ru.practicum.ewm.stat_svc.dto.error.exception.EwmException;

public class OtherException extends EwmException {
    public OtherException(Exception exception) {
        httpErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        cause = exception.getCause().getMessage();
        message = exception.getMessage();
    }
}
