package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.CategoryCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.CategoryUpdateReq;
import ru.practicum.ewm.main_svc.model.dto.resp.CategoryResp;
import ru.practicum.ewm.main_svc.model.entity.Category;

public interface CategoryService {
    Category create(CategoryCreateReq categoryCreateReq);

    Category update(Long categoryId, CategoryUpdateReq updateReq);

    void delete(Long categoryId);

    Iterable<CategoryResp> findAll(Integer from, Integer size);

    CategoryResp find(Long categoryId);
}
