package ru.practicum.ewm.main_svc.model.util.mappers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.main_svc.model.dto.CategoryDto;
import ru.practicum.ewm.main_svc.model.dto.NewCategoryDto;
import ru.practicum.ewm.main_svc.model.entity.Category;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryMapper {
    final ModelMapper modelMapper;

    public Category newCategoryDto2entity(NewCategoryDto newCategoryDto) {
        return Optional.ofNullable(newCategoryDto).map(obj -> modelMapper.map(obj, Category.class)).orElse(null);
    }

    public CategoryDto entity2categoryDto(Category category) {
        return Optional.ofNullable(category).map(obj -> modelMapper.map(obj, CategoryDto.class)).orElse(null);
    }
}
