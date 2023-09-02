package ru.practicum.ewm.main_svc.controller.adm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAdminController {
    CategoryService categoryService;

    public CategoryDto adminCreate(NewCategoryDto newCategoryDto) {
        return categoryService.adminCreate(newCategoryDto);
    }

    public void adminDelete(Long catId) {
        categoryService.adminDelete(catId);
    }

    public CategoryDto adminUpdate(Long catId, CategoryDto categoryDto) {
        return categoryService.adminUpdate(catId, categoryDto);
    }
}
