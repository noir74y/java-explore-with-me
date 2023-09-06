package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByInitiatorId(Long userId, PageRequest pageRequest);

    Event findByInitiatorIdAndId(Long userId, Long eventId);
}
