package ru.practicum.ewm.main_svc.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.MainEwmException;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.model.util.mappers.CategoryMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;
    final CategoryMapper categoryMapper;

    @Override
    public CategoryDto adminCreateCategory(NewCategoryDto newCategoryDto) {
        return categoryMapper.entity2categoryDto(categoryRepository.save(categoryMapper.newCategoryDto2entity(newCategoryDto)));
    }

    @Override
    public void adminDeleteCategory(Long catId) {
        if (categoryRepository.existsById(catId)) categoryRepository.deleteById(catId);
        else
            throw new MainEwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND);
    }

    @Override
    public CategoryDto adminUpdateCategory(Long catId, CategoryDto categoryDto) {
        var category = categoryRepository.findById(catId)
                .orElseThrow(() -> new MainEwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND));

        if (!categoryDto.getName().equals(category.getName())) {
            category.setName(categoryDto.getName());
            category = categoryRepository.save(category);
        }

        return categoryMapper.entity2categoryDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> publicFindAllCategories(Integer from, Integer size) {
        return categoryRepository
                .findAll(PageRequest.of(from / size, size))
                .stream()
                .map(categoryMapper::entity2categoryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto publicFindCategoryById(Long catId) {
        return categoryMapper.entity2categoryDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new MainEwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND)));
    }
}
