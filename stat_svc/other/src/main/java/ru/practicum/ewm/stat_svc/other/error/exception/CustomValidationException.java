package ru.practicum.ewm.stat_svc.other.error.exception;

import org.springframework.http.HttpStatus;

public class CustomValidationException extends EwmException {
    public CustomValidationException(String message, String details) {
        httpErrorStatus = HttpStatus.BAD_REQUEST;
        this.cause = message;
        this.message = details;
    }
}

