package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.model.entity.Compilation;
import ru.practicum.ewm.main_svc.repository.EventRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationMapper {
    final ModelMapper modelMapper;
    final EventRepository eventRepository;
    final EventMapper eventMapper;

    public Compilation newCompilationDto2entity(NewCompilationDto newCompilationDto) {
        assert newCompilationDto != null;
        var compilation = modelMapper.map(newCompilationDto, Compilation.class);

        compilation.setEvents(eventRepository
                .findAllByIdIn(Optional.ofNullable(newCompilationDto.getEvents())
                        .orElse(Collections.emptySet()))
                .orElse(Collections.emptySet()));

        if (Optional.ofNullable(newCompilationDto.getEvents()).orElse(Collections.emptySet()).size() != compilation.getEvents().size())
            throw new MainEwmException("some events are not found", HttpStatus.CONFLICT);

        return compilation;
    }

    public void updateCompilationRequest2entity(Compilation compilation, UpdateCompilationRequest updateCompilationRequest) {

        var events = eventRepository.findAllByIdIn(Optional.ofNullable(updateCompilationRequest.getEvents())
                .orElse(Collections.emptySet())).orElse(Collections.emptySet());

        if (Optional.ofNullable(updateCompilationRequest.getEvents()).orElse(Collections.emptySet()).size() != events.size())
            throw new MainEwmException("some events are not found", HttpStatus.CONFLICT);

        compilation.setEvents(events);
        compilation.setTitle(Optional.ofNullable(updateCompilationRequest.getTitle()).orElse(compilation.getTitle()));
        compilation.setPinned(Optional.ofNullable(updateCompilationRequest.getPinned()).orElse(compilation.getPinned()));
    }

    public CompilationDto entity2compilationDto(Compilation compilation) {
        var compilationDto = modelMapper.map(compilation, CompilationDto.class);
        compilationDto.setEvents(compilation
                .getEvents()
                .stream()
                .map(eventMapper::entity2eventShortDto)
                .collect(Collectors.toSet()));
        return compilationDto;
    }
}
