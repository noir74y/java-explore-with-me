package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Event;
import ru.practicum.ewm.main_svc.model.entity.Subscription;
import ru.practicum.ewm.main_svc.model.util.enums.FriendshipStatus;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserIdAndFriendId(Long userId, Long friendId);

    Optional<List<Subscription>> findAllByUserId(Long userId);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.user.id AND f.status = :friendshipStatus " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = :eventState " +
            "WHERE s.user.id = :userId"
    )
    Optional<List<Event>> findAllEventsByFriendsInitiators(Long userId,
                                                           FriendshipStatus friendshipStatus,
                                                           String eventState);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.user.id AND f.status = :friendshipStatus " +
            "JOIN Request r ON r.requestor.id = f.friend2.id AND r.status = :requestStatus " +
            "JOIN Event e ON e.id = r.event.id AND e.state = :eventState " +
            "WHERE s.user.id = :userId")
    Optional<List<Event>> findAllEventsByFriendsParticipants(Long userId,
                                                             FriendshipStatus friendshipStatus,
                                                             RequestStatus requestStatus,
                                                             String eventState);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.user.id AND f.status = :friendshipStatus " +
            "JOIN Event e ON e.initiator.id = f.friend2.id AND e.state = :eventState " +
            "WHERE s.user.id = :userId AND s.friend.id = :friendId")
    Optional<List<Event>> findAllEventsByFriendInitiator(Long userId,
                                                         Long friendId,
                                                         FriendshipStatus friendshipStatus,
                                                         String eventState);

    @Query("SELECT e " +
            "FROM Subscription s " +
            "JOIN Friendship f ON f.friend1.id = s.user.id AND f.status = :friendshipStatus " +
            "JOIN Request r ON r.requestor.id = f.friend2.id AND r.status = :requestStatus " +
            "JOIN Event e ON e.id = r.event.id AND e.state = :eventState " +
            "WHERE s.user.id = :userId AND s.friend.id = :friendId")
    Optional<List<Event>> findAllEventsByFriendParticipant(Long userId,
                                                           Long friendId,
                                                           FriendshipStatus friendshipStatus,
                                                           RequestStatus requestStatus,
                                                           String eventState);
}
