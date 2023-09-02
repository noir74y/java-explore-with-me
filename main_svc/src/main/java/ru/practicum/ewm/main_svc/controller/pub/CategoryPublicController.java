package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryPublicController {
    CategoryService categoryService;

    @GetMapping
    public Iterable<CategoryDto> publicFindAll(@RequestParam(defaultValue = "0") Integer from,
                                               @RequestParam(defaultValue = "10") Integer size) {
        return categoryService.publicFindAll(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto publicFindById(@PathVariable @NotNull Long catId) {
        return categoryService.publicFindById(catId);
    }
}
