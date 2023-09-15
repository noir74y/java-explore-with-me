package ru.practicum.ewm.main_svc.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAdminController {
    final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoryDto adminCreateCategory(@RequestBody @NotNull @Valid NewCategoryDto newCategoryDto) {
        log.info("POST /admin/categories {}", newCategoryDto);
        return categoryService.adminCreateCategory(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void adminDeleteCategory(@PathVariable @NotNull Long catId) {
        log.info("DELETE /admin/categories/{}", catId);
        categoryService.adminDeleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto adminUpdateCategory(@PathVariable @NotNull Long catId,
                                           @RequestBody @NotNull @Valid CategoryDto categoryDto) {
        log.info("PATH /admin/categories/{} {}", catId, categoryDto);
        return categoryService.adminUpdateCategory(catId, categoryDto);
    }
}
