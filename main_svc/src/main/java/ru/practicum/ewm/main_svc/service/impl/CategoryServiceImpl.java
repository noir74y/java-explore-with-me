package ru.practicum.ewm.main_svc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.main_svc.error.EwmException;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.model.util.mappers.CategoryMapper;
import ru.practicum.ewm.main_svc.repository.CategoryRepository;
import ru.practicum.ewm.main_svc.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto adminCreate(NewCategoryDto newCategoryDto) {
        return categoryMapper.entity2categoryDto(categoryRepository.save(categoryMapper.categoryDto2entity(newCategoryDto)));
    }

    @Override
    @Transactional
    public void adminDelete(Long catId) {
        if (categoryRepository.existsById(catId)) categoryRepository.deleteById(catId);
        else throw new EwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public CategoryDto adminUpdate(Long catId, CategoryDto categoryDto) {
        var category = categoryRepository.findById(catId)
                .orElseThrow(() -> new EwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND));

        if (!categoryDto.getName().equals(category.getName())) {
            category.setName(categoryDto.getName());
            category = categoryRepository.save(category);
        }

        return categoryMapper.entity2categoryDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> publicFindAll(Integer from, Integer size) {
        return categoryRepository
                .findAll(PageRequest.of(from / size, size))
                .stream()
                .map(categoryMapper::entity2categoryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto publicFindById(Long catId) {
        return categoryMapper.entity2categoryDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new EwmException(String.format("Category with id=%d was not found.", catId), HttpStatus.NOT_FOUND)));
    }
}
