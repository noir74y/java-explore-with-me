package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.CompilationCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.CompilationUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.resp.CompilationResp;
import ru.practicum.ewm.main_svc.model.entity.Compilation;

import java.util.List;

public interface CompilationService {
    Iterable<CompilationResp> findAll(Boolean pinned, Integer from, Integer size);

    Compilation find(Long compilationId);
}
