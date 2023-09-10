package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    @NotBlank
    @ValueOfEnumConstraint(enumClass = EventUserState.class)
    private String stateAction;
}
