package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
