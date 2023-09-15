package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.util.MainAppConfig;
import ru.practicum.ewm.main_svc.service.CategoryService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryPublicController {
    final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> publicFindAllCategories(@RequestParam(defaultValue = MainAppConfig.FROM) @PositiveOrZero Integer from,
                                                     @RequestParam(defaultValue = MainAppConfig.SIZE) @Positive Integer size) {
        log.info("GET /categories {} {}", from, size);
        return categoryService.publicFindAllCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto publicFindCategoryById(@PathVariable @NotNull Long catId) {
        log.info("GET /categories/{}", catId);
        return categoryService.publicFindCategoryById(catId);
    }
}
