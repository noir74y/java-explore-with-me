package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRequestStatusUpdateReq {
    List<Long> requestsIdList;
    @ValueOfEnumConstraint(enumClass = RequestStatus.class)
    String status;
}
