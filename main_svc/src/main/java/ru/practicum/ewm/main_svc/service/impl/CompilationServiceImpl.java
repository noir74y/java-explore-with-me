package ru.practicum.ewm.main_svc.service.impl;

import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.service.CompilationService;

public class CompilationServiceImpl implements CompilationService {
    @Override
    public Iterable<CompilationDto> publicFindAll(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    public CompilationDto publicFindById(Long compId) {
        return null;
    }

    @Override
    public CompilationDto adminCreate(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void adminDelete(Long compId) {

    }

    @Override
    public CompilationDto adminUpdate(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
