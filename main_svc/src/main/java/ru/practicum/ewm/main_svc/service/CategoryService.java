package ru.practicum.ewm.main_svc.service;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;

@Service
public interface CategoryService {
    CategoryDto adminCreate(NewCategoryDto newCategoryDto);

    void adminDelete(Long catId);

    CategoryDto adminUpdate(Long catId, CategoryDto categoryDto);

    Iterable<CategoryDto> publicFindAll(Integer from, Integer size);

    CategoryDto publicFindById(Long categoryId);
}
