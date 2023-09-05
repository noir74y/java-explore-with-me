package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.util.AppConfig;
import ru.practicum.ewm.main_svc.service.CategoryService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryPublicController {
    CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> publicFindAll(@RequestParam(defaultValue = AppConfig.FROM) Integer from,
                                           @RequestParam(defaultValue = AppConfig.SIZE) Integer size) {
        return categoryService.publicFindAll(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto publicFindById(@PathVariable @NotNull Long catId) {
        return categoryService.publicFindById(catId);
    }
}
