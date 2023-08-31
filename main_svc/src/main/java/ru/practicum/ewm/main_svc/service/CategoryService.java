package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.CategoryCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.CategoryUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Category;

public interface CategoryService {
    Category create(CategoryCreateReq req);

    Category update(CategoryUpdateReq req);

    void delete(Long id);
}
