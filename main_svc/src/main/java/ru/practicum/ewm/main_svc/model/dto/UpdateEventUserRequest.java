package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    @ValueOfEnumConstraint(enumClass = EventUserState.class)
    private String stateAction;
}
