package ru.practicum.ewm.main_svc.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.main_svc.model.entity.User;
import ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendshipDto {
    Long id;
    User friend1;
    User friend2;
    FriendshipStatus status;
}
