package ru.practicum.ewm.stat_svc.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOutView;
import ru.practicum.ewm.stat_svc.dto.model.HitEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<HitEntity, Integer> {
    @Query(value = "SELECT app, uri, COUNT(ip) AS hits FROM stats WHERE created BETWEEN ?1 AND ?2 AND uris IN (?3) GROUP BY app, uri", nativeQuery = true)
    List<DtoHitOutView> getHitsWithAllIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "SELECT app, uri, COUNT(DISTINCT ip) AS hits FROM stats WHERE created BETWEEN ?1 AND ?2 AND uris IN (?3) GROUP BY app, uri", nativeQuery = true)
    List<DtoHitOutView> getHitsWithUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);
}
