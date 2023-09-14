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
    Optional<Subscription> findByUserIdAndFriendId(Long userId, Long friendId);

    Optional<List<Subscription>> findAllByUserId(Long userId);

    @Query("")
    Optional<List<Event>> findAllEventsByFriendsInitiators(Long userId);

    @Query("")
    Optional<List<Event>> findAllEventsByFriendsParticipants(Long userId);

    @Query("")
    Optional<List<Event>> findAllEventsByFriendInitiator(Long userId, Long friendId);

    @Query("")
    Optional<List<Event>> findAllEventsByFriendParticipant(Long userId, Long friendId);
}
