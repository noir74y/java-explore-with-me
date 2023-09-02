package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.service.CompilationService;

@Slf4j
@Controller
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationAdminController {
    CompilationService compilationService;

    public CompilationDto adminCreate(NewCompilationDto newCompilationDto) {
        return compilationService.adminCreate(newCompilationDto);
    }

    public void adminDelete(Long compId) {
        compilationService.adminDelete(compId);
    }

    public CompilationDto adminUpdate(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.adminUpdate(compId, updateCompilationRequest);
    }
}
