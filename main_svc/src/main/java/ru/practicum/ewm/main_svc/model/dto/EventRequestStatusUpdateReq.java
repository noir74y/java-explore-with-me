package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;
import ru.practicum.ewm.main_svc.model.util.validation.ValueOfEnumConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestStatusUpdateReq {
    List<Long> requestIds;
    String status;
}
