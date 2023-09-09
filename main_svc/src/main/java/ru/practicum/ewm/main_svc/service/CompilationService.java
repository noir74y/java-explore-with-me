package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;

import java.util.List;

@Service
public interface CompilationService {
    List<CompilationDto> publicFindAllCompilations(Boolean pinned,
                                                   Integer from,
                                                   Integer size);

    CompilationDto publicFindCompilationById(Long compId);

    CompilationDto adminCreateCompilation(NewCompilationDto newCompilationDto);

    void adminDeleteCompilation(Long compId);

    CompilationDto adminUpdateCompilation(Long compId,
                                          UpdateCompilationRequest updateCompilationRequest);

}
