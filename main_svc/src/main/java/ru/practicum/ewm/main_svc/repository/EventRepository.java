package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByIdAndState(Long id, String state);

    List<Event> findAllByInitiatorId(Long initiatorId, PageRequest pageRequest);

    Event findByInitiatorIdAndId(Long initiatorId, Long eventId);

    @Query("SELECT e FROM Event e " +
            "WHERE (:initiators IS NULL OR e.initiator.id IN :initiators) " +
            "AND (:states IS NULL OR e.state IN :states) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "AND e.eventDate BETWEEN :rangeStart AND :rangeEnd " +
            "ORDER BY e.createdOn DESC")
    Optional<List<Event>> adminFindEvents(List<Long> initiators,
                                          List<String> states,
                                          List<Long> categories,
                                          LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd,
                                          Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE e.state IN :state " +
            "AND (:searchPattern IS NULL " +
            "OR LOWER(e.annotation) LIKE CONCAT('%',:searchPattern,'%') " +
            "OR LOWER(e.description) LIKE CONCAT('%',:searchPattern,'%')) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "AND (:paid IS NULL OR e.paid IN :paid) " +
            "AND e.eventDate BETWEEN :rangeStart AND :rangeEnd")
    Optional<List<Event>> publicFindEvents(String state,
                                           String searchPattern,
                                           Iterable<Long> categories,
                                           Boolean paid,
                                           LocalDateTime rangeStart,
                                           LocalDateTime rangeEnd,
                                           Pageable pageable);
}
