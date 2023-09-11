package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.entity.Compilation;
import ru.practicum.ewm.main_svc.repository.EventRepository;

import java.util.Collections;
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
                .findAllByIdIn(newCompilationDto.getEventsOptional())
                .orElse(Collections.emptySet()));
        return compilation;
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
