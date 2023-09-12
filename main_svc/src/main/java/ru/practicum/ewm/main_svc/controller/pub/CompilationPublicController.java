package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;
import ru.practicum.ewm.main_svc.service.CompilationService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationPublicController {
    final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> publicFindAllCompilations(@RequestParam(required = false) Boolean pinned,
                                                          @RequestParam(defaultValue = MainAppConfig.FROM) @PositiveOrZero Integer from,
                                                          @RequestParam(defaultValue = MainAppConfig.SIZE) @Positive Integer size) {
        log.info("GET /compilations {} {} {}", pinned, from, size);
        return compilationService.publicFindAllCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto publicFindCompilationById(@PathVariable @NotNull Long compId) {
        log.info("GET /compilations/{}", compId);
        return compilationService.publicFindCompilationById(compId);
    }
}

