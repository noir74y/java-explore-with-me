package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Compilation;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
