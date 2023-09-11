package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.model.util.mappers.CompilationMapper;
import ru.practicum.ewm.main_svc.repository.CompilationRepository;
import ru.practicum.ewm.main_svc.service.CompilationService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationServiceImpl implements CompilationService {
    final CompilationRepository compilationRepository;
    final CompilationMapper compilationMapper;

    @Override
    public CompilationDto adminCreateCompilation(NewCompilationDto newCompilationDto) {
        return compilationMapper.entity2compilationDto(compilationRepository.save(compilationMapper.newCompilationDto2entity(newCompilationDto)));
    }

    @Override
    public CompilationDto adminUpdateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }

    @Override
    public void adminDeleteCompilation(Long compId) {
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompilationDto> publicFindAllCompilations(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto publicFindCompilationById(Long compId) {
        return null;
    }
}
