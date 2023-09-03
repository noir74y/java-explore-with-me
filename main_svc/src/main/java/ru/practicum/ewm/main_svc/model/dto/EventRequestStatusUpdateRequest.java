package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRequestStatusUpdateRequest {
    Iterable<Long> requestIds;
    @ValueOfEnumConstraint(enumClass = RequestStatus.class)
    String status;
}
