package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.main_svc.model.entity.User;

public interface PersonRepository extends JpaRepository<User, Long> {
}
