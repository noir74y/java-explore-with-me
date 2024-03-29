package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.dto.NewCompilationDto;
import ru.practicum.ewm.main_svc.model.dto.UpdateCompilationRequest;
import ru.practicum.ewm.main_svc.service.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationAdminController {
    final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompilationDto adminCreateCompilation(@RequestBody @NotNull @Valid NewCompilationDto newCompilationDto) {
        log.info("POST /admin/compilations {}", newCompilationDto);
        return compilationService.adminCreateCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void adminDeleteCompilation(@PathVariable @NotNull Long compId) {
        log.info("DELETE /admin/{}", compId);
        compilationService.adminDeleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto adminUpdateCompilation(@PathVariable @NotNull Long compId,
                                                 @RequestBody @NotNull @Valid UpdateCompilationRequest updateCompilationRequest) {
        log.info("DELETE /admin/{} {}", compId, updateCompilationRequest);
        return compilationService.adminUpdateCompilation(compId, updateCompilationRequest);
    }
}
