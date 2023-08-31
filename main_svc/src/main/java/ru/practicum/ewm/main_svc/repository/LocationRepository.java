package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.main_svc.model.entity.Category;
import ru.practicum.ewm.main_svc.model.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
