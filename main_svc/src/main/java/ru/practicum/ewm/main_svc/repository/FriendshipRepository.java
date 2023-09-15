package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Friendship;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findByFriend1IdAndFriend2Id(Long userId, Long friendId);

    Boolean existsByFriend1IdAndFriend2Id(Long userId, Long friendId);

    Optional<List<Friendship>> findAllByFriend1Id(Long userId);
}
