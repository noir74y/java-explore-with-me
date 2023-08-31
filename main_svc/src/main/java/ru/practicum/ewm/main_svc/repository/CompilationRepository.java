package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.main_svc.model.entity.Compilation;
import ru.practicum.ewm.main_svc.model.entity.Location;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
