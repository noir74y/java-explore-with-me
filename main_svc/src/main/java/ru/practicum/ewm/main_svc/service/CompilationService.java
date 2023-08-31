package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.CompilationCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.CompilationUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Compilation;

public interface CompilationService {
    Compilation create(CompilationCreateReq req);

    Compilation update(CompilationUpdateReq req);

    void delete(Long id);
}
