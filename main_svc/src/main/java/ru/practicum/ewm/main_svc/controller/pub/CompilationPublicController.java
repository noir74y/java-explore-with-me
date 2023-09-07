package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CompilationDto;
import ru.practicum.ewm.main_svc.service.CompilationService;

import javax.validation.constraints.NotNull;
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
                                              @RequestParam(defaultValue = "0") Integer from,
                                              @RequestParam(defaultValue = "10") Integer size) {
        return compilationService.publicFindAll(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto publicFindById(@PathVariable @NotNull Long compId) {
        return compilationService.publicFindById(compId);
    }
}

