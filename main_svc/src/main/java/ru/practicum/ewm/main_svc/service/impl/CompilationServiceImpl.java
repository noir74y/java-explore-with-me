package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.model.util.mappers.CompilationMapper;
import ru.practicum.ewm.main_svc.repository.CompilationRepository;
import ru.practicum.ewm.main_svc.repository.EventRepository;
import ru.practicum.ewm.main_svc.service.CompilationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationServiceImpl implements CompilationService {
    final CompilationRepository compilationRepository;
    final CompilationMapper compilationMapper;
    private final EventRepository eventRepository;

    @Override
    public CompilationDto adminCreateCompilation(NewCompilationDto newCompilationDto) {
        return compilationMapper.entity2compilationDto(compilationRepository.save(compilationMapper.newCompilationDto2entity(newCompilationDto)));
    }

    @Override
    public CompilationDto adminUpdateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        var compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new MainEwmException(String.format("there is no compilation with id=%d", compId), HttpStatus.NOT_FOUND));
        compilationMapper.updateCompilationRequest2entity(compilation, updateCompilationRequest);
        return compilationMapper.entity2compilationDto(compilationRepository.save(compilation));
    }

    @Override
    public void adminDeleteCompilation(Long compId) {
        if (!compilationRepository.existsById(compId))
            throw new MainEwmException(String.format("there is no compilation with id=%d", compId), HttpStatus.NOT_FOUND);
        compilationRepository.deleteById(compId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompilationDto> publicFindAllCompilations(Boolean pinned, Integer from, Integer size) {
        return compilationRepository
                .findAll(PageRequest.of(from / size, size))
                .getContent()
                .stream()
                .map(compilationMapper::entity2compilationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto publicFindCompilationById(Long compId) {
        return compilationMapper
                .entity2compilationDto(compilationRepository.findById(compId)
                        .orElseThrow(() -> new MainEwmException(String.format("there is no compilation with id=%d", compId), HttpStatus.NOT_FOUND)));
    }
}
