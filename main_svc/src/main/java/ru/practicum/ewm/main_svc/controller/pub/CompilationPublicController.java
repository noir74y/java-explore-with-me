package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.model.util.AppConfig;
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
    CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> publicFindAll(@RequestParam Boolean pinned,
                                              @RequestParam(defaultValue = AppConfig.FROM) @PositiveOrZero Integer from,
                                              @RequestParam(defaultValue = AppConfig.SIZE) @Positive Integer size) {
        return compilationService.publicFindAll(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto publicFindById(@PathVariable @NotNull Long compId) {
        return compilationService.publicFindById(compId);
    }
}

