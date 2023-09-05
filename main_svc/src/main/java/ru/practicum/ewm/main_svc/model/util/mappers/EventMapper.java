package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventMapper {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LocationMapper locationMapper;

    public Event newEventDto2entity(Long userId, NewEventDto newEventDto) {
        return Optional.ofNullable(newEventDto).map(obj -> modelMapper.map(obj, Event.class)).orElse(null);
    }

    public EventShortDto entity2eventShortDto(Event event) {
        return Optional.ofNullable(event).map(obj -> modelMapper.map(obj, EventShortDto.class)).orElse(null);
    }

    public EventFullDto entity2eventFullDto(Event event) {
        return Optional.ofNullable(event).map(obj -> modelMapper.map(obj, EventFullDto.class)).orElse(null);
    }
}
