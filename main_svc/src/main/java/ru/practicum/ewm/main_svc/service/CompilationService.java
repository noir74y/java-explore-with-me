package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;

public interface CompilationService {
    Iterable<CompilationDto> publicFindAll(Boolean pinned, Integer from, Integer size);

    CompilationDto publicFindById(Long compId);

    CompilationDto adminCreate(NewCompilationDto newCompilationDto);

    void adminDelete(Long compId);

    CompilationDto adminUpdate(Long compId, UpdateCompilationRequest updateCompilationRequest);

}
