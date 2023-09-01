package ru.practicum.ewm.main_svc.service.impl;

import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDto adminCreate(NewCategoryDto newCategoryDto) {
        return null;
    }

    @Override
    public void adminDelete(Long catId) {

    }

    @Override
    public CategoryDto adminUpdate(Long catId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public Iterable<CategoryDto> publicFindAll(Integer from, Integer size) {
        return null;
    }

    @Override
    public CategoryDto publicFindById(Long categoryId) {
        return null;
    }
}
