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

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.subscriber.id AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = 'PUBLISHED' " +
            "WHERE s.subscriber.id = :subscriberId"
    )
    Optional<List<Event>> findAllEventsByPersonsInitiators(Long subscriberId);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.subscriber.id AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "JOIN Request r ON r.requestor.id = f.friend2.id AND r.status = ru.practicum.ewm.main_svc.model.util.enums.RequestStatus.CONFIRMED " +
            "JOIN Event e ON e.id = r.event.id AND e.state = 'PUBLISHED' " +
            "WHERE s.subscriber.id = :subscriberId")
    Optional<List<Event>> findAllEventsByPersonsParticipants(Long subscriberId);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.subscriber.id AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = 'PUBLISHED' " +
            "WHERE s.subscriber.id = :subscriberId AND s.person.id = :personId")
    Optional<List<Event>> findAllEventsByPersonInitiator(Long subscriberId, Long personId);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.subscriber.id AND f.status = ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus.CONFIRMED " +
            "JOIN Request r ON r.requestor.id = f.friend2.id AND r.status = ru.practicum.ewm.main_svc.model.util.enums.RequestStatus.CONFIRMED " +
            "JOIN Event e ON e.id = r.event.id AND e.state = 'PUBLISHED' " +
            "WHERE s.subscriber.id = :subscriberId AND s.person.id = :personId")
    Optional<List<Event>> findAllEventsByPersonParticipant(Long subscriberId, Long personId);
}
