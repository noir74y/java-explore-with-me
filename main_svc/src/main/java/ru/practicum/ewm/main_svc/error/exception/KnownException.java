package ru.practicum.ewm.main_svc.error.exception;

import org.springframework.http.HttpStatus;

public class KnownException extends EwmException {
    public KnownException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }
}
