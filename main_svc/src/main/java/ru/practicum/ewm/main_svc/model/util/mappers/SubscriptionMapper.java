package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.SubscriptionDto;
import ru.practicum.ewm.main_svc.model.entity.Subscription;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionMapper {
    final ModelMapper modelMapper;

    public SubscriptionDto toDto(Subscription subscription) {
        return Optional.ofNullable(subscription).map(obj -> modelMapper.map(obj, SubscriptionDto.class)).orElse(null);
    }
}
