package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto adminCreateCategory(NewCategoryDto newCategoryDto);

    void adminDeleteCategory(Long catId);

    CategoryDto adminUpdateCategory(Long catId,
                                    CategoryDto categoryDto);

    List<CategoryDto> publicFindAllCategories(Integer from,
                                              Integer size);

    CategoryDto publicFindCategoryById(Long catId);
}
