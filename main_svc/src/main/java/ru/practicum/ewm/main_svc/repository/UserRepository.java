package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Iterable<User> findByIdIn(Iterable<Long> ids, PageRequest pageRequest);
}
