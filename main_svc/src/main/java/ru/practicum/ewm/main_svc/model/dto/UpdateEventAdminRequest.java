package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.enums.EventAdminState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventAdminRequest extends UpdateEventRequest {
    @ValueOfEnumConstraint(enumClass = EventAdminState.class)
    private String stateAction;
}
