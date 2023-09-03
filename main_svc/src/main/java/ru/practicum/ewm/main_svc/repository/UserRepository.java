package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.dto.UserDto;
import ru.practicum.ewm.main_svc.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT new ru.practicum.ewm.main_svc.model.dto.UserDto(u.id, u.name. u.mail) " +
            "FROM User u " +
            "WHERE u.id IN :ids")
    Iterable<UserDto> adminFindByIds(Iterable<Long> ids);
}
