package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.service.CompilationService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompilationServiceImpl implements CompilationService {
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

    @Override
    public CompilationDto adminCreateCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void adminDeleteCompilation(Long compId) {
    }

    @Override
    public CompilationDto adminUpdateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
