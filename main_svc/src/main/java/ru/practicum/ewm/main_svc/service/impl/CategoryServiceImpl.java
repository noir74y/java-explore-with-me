package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.model.util.mappers.CategoryMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto adminCreate(NewCategoryDto newCategoryDto) {
        return categoryMapper.entity2categoryDto(categoryRepository.save(categoryMapper.categoryDto2entity(newCategoryDto)));
    }

    @Override
    public void adminDelete(Long catId) {
        categoryRepository.deleteById(catId);
    }

    @Override
    public CategoryDto adminUpdate(Long catId, CategoryDto categoryDto) {
        var category = categoryMapper.categoryDto2entity(categoryDto);
        category.setId(catId);
        return categoryMapper.entity2categoryDto(categoryRepository.save(category));
    }

    @Override
    public Iterable<CategoryDto> publicFindAll(Integer from, Integer size) {
        return null;
    }

    @Override
    public CategoryDto publicFindById(Long catId) {
        return null;
    }
}
