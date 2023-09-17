package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.entity.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriberIdAndPersonId(Long subscriberId, Long personId);

    Optional<List<Subscription>> findAllBySubscriberId(Long subscriberId);

    @Query("SELECT e FROM Friendship f " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = 'PUBLISHED' " +
            "WHERE f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "AND f.friend1.id = :subscriberId " +
            "ORDER BY e.id")
    Optional<List<Event>> findAllEventsByPersonsInitiators(Long subscriberId);

    @Query("SELECT e FROM Friendship f " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = 'PUBLISHED' " +
            "WHERE f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "AND f.friend1.id = :subscriberId AND f.friend2.id = :personId " +
            "ORDER BY e.id")
    Optional<List<Event>> findAllEventsByPersonInitiator(Long subscriberId, Long personId);

    @Query("SELECT e FROM Event e, Friendship f, Request r " +
            "WHERE e.id = r.event.id AND e.state = 'PUBLISHED' " +
            "AND r.requestor.id = f.friend2.id AND r.status = ru.practicum.ewm.main_svc.model.util.enums.RequestStatus.CONFIRMED " +
            "AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "AND f.friend1.id = :subscriberId " +
            "ORDER BY e.id")
    Optional<List<Event>> findAllEventsByPersonsParticipants(Long subscriberId);

    @Query("SELECT e FROM Event e, Friendship f, Request r " +
            "WHERE e.id = r.event.id AND e.state = 'PUBLISHED' " +
            "AND r.requestor.id = f.friend2.id AND r.status = ru.practicum.ewm.main_svc.model.util.enums.RequestStatus.CONFIRMED " +
            "AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "AND f.friend1.id = :subscriberId AND f.friend2.id = :personId " +
            "ORDER BY e.id")
    Optional<List<Event>> findAllEventsByPersonParticipant(Long subscriberId, Long personId);
}
