package ru.practicum.ewm.main_svc.error.exception;

import org.springframework.http.HttpStatus;

public class UnknownException extends EwmException {
    public UnknownException() {
        super("UnknownException", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
