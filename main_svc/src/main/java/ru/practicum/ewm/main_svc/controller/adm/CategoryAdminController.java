package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAdminController {
    CategoryService categoryService;

    @PostMapping
    public CategoryDto adminCreate(@RequestBody @NotNull @Valid NewCategoryDto newCategoryDto) {
        return categoryService.adminCreate(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    public void adminDelete(@PathVariable @NotNull Long catId) {
        categoryService.adminDelete(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto adminUpdate(@PathVariable @NotNull Long catId,
                                   @RequestBody @NotNull @Valid CategoryDto categoryDto) {
        return categoryService.adminUpdate(catId, categoryDto);
    }
}
