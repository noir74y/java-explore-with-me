package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.enums.EventUserState;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventUserRequest extends UpdateEventRequest {
    @ValueOfEnumConstraint(enumClass = EventUserState.class)
    String stateAction;
}
