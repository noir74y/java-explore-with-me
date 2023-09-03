package ru.practicum.ewm.main_svc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    @ValueOfEnumConstraint(enumClass = EventUserState.class)
    private String stateAction;
}
