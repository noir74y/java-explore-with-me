package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.service.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationAdminController {
    CompilationService compilationService;

    @PostMapping
    public CompilationDto adminCreate(@RequestBody @NotNull @Valid NewCompilationDto newCompilationDto) {
        return compilationService.adminCreate(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void adminDelete(@PathVariable @NotNull Long compId) {
        compilationService.adminDelete(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto adminUpdate(@PathVariable @NotNull Long compId,
                                      @RequestBody @NotNull @Valid UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.adminUpdate(compId, updateCompilationRequest);
    }
}
