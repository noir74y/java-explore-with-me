package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.service.CompilationService;

@Slf4j
@Controller
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationPublicController {
    CompilationService compilationService;

    public Iterable<CompilationDto> publicFindAll(Boolean pinned, Integer from, Integer size) {
        return compilationService.publicFindAll(pinned, from, size);
    }

    public CompilationDto publicFindById(Long compId) {
        return compilationService.publicFindById(compId);
    }
}

