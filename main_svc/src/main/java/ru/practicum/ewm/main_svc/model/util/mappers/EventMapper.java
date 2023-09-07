package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.EventFullDto;
import ru.practicum.ewm.main_svc.model.dto.EventShortDto;
import ru.practicum.ewm.main_svc.model.dto.NewEventDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateEventUserRequest;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.util.enums.EventState;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.repository.LocationRepository;
import ru.practicum.ewm.main_svc.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventMapper {
    final ModelMapper modelMapper;
    final CategoryRepository categoryRepository;
    final UserRepository userRepository;
    final LocationMapper locationMapper;
    final LocationRepository locationRepository;

    public Event newEventDto2entity(Long initiatorId,
                                    NewEventDto newEventDto) {
        var event = modelMapper.map(newEventDto, Event.class);
        event.setState(EventState.PENDING);
        event.setInitiator(userRepository.findById(initiatorId).orElse(null));
        event.setCategory(categoryRepository.findById(newEventDto.getCatId()).orElse(null));
        event.setLocation(locationRepository.save(locationMapper.locationDto2entity(newEventDto.getLocationDto())));
        event.setCreatedOn(LocalDateTime.now());
        return event;
    }

    public Event updateEventUserRequest2entity(UpdateEventUserRequest updateEventUserRequest) {
        var event = modelMapper.map(updateEventUserRequest, Event.class);
        return event;
    }

    public EventShortDto entity2eventShortDto(Event event) {
        return Optional.ofNullable(event).map(obj -> modelMapper.map(obj, EventShortDto.class)).orElse(null);
    }

    public EventFullDto entity2eventFullDto(Event event) {
        return Optional.ofNullable(event).map(obj -> modelMapper.map(obj, EventFullDto.class)).orElse(null);
    }
}
