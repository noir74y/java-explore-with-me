package ru.practicum.ewm.main_svc.error;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}