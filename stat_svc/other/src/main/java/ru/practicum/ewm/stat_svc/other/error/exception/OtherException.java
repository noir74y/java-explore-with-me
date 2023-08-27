package ru.practicum.ewm.stat_svc.other.error.exception;

import org.springframework.http.HttpStatus;

public class OtherException extends EwmException {
    public OtherException(Exception exception) {
        httpErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        cause = exception.getCause().getMessage();
        message = exception.getMessage();
    }
}
