package ru.practicum.ewm.main_svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.main_svc.model.entity.Request;
import ru.practicum.ewm.main_svc.model.util.enums.RequestStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    boolean existsByRequestorIdAndEventIdAndStatus(Long requestorId, Long eventId, RequestStatus status);

    long countByEventIdAndStatus(Long eventId, RequestStatus status);

    Optional<Request> findByIdAndRequestorIdAndStatusIn(Long requestId, Long RequestorId, List<RequestStatus> statuses);

    Optional<List<Request>> findAllByRequestorId(Long requestorId);
}
