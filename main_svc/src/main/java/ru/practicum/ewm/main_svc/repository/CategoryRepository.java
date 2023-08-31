package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.main_svc.model.entity.Category;
import ru.practicum.ewm.main_svc.model.entity.Person;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
