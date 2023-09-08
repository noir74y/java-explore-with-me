package ru.practicum.ewm.main_svc.controller.pub;

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
    public List<CategoryDto> publicFindAll(@RequestParam(defaultValue = MainAppConfig.FROM) @PositiveOrZero Integer from,
                                           @RequestParam(defaultValue = MainAppConfig.SIZE) @Positive Integer size) {
        return categoryService.publicFindAll(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto publicFindById(@PathVariable @NotNull Long catId) {
        return categoryService.publicFindById(catId);
    }
}
