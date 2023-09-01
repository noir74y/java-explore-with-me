package ru.practicum.ewm.main_svc.model.dto;

import ru.practicum.ewm.main_svc.model.enums.RequestStatus;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UpdateEventUserRequest {
    @Size(min = 20, max = 2000)
    String annotation;

    Long category;

    @Size(min = 20, max = 7000)
    String description;

    LocalDateTime eventDate;
    LocationDto location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;

    RequestStatus stateAction;

    @Size(min = 3, max = 120)
    String title;
}
