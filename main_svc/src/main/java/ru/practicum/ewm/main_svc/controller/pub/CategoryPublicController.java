package ru.practicum.ewm.main_svc.controller.pub;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

@Slf4j
@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryPublicController {
    CategoryService categoryService;

    public Iterable<CategoryDto> publicFindAll(Integer from, Integer size) {
        return categoryService.publicFindAll(from, size);
    }

    public CategoryDto publicFindById(Long categoryId) {
        return categoryService.publicFindById(categoryId);
    }
}
