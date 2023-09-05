package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.model.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public Category categoryDto2entity(NewCategoryDto newCategoryDto) {
        return Optional.ofNullable(newCategoryDto).map(obj -> modelMapper.map(obj, Category.class)).orElse(null);
    }

    public Category categoryDto2entity(CategoryDto categoryDto) {
        return Optional.ofNullable(categoryDto).map(obj -> modelMapper.map(obj, Category.class)).orElse(null);
    }

    public CategoryDto entity2categoryDto(Category category) {
        return Optional.ofNullable(category).map(obj -> modelMapper.map(obj, CategoryDto.class)).orElse(null);
    }
}
