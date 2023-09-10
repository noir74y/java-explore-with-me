package ru.practicum.ewm.main_svc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.main_svc.model.util.enums.EventAdminState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventAdminRequest extends UpdateEventRequest {
    @ValueOfEnumConstraint(enumClass = EventAdminState.class)
    private String stateAction;
}
