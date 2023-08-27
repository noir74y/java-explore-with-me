package ru.practicum.ewm.stat_svc.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.model.HitEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<HitEntity, Long> {
    @Query(value = "SELECT new ru.practicum.ewm.stat_svc.dto.model.DtoHitOut(hit.app, hit.uri, COUNT(hit.ip)) " +
            "FROM HitEntity AS hit " +
            "WHERE hit.timestamp BETWEEN :start AND :end " +
            "AND COALESCE(:uris, NULL) IS NULL OR hit.uri IN :uris " +
            "GROUP BY hit.app, hit.uri " +
            "ORDER BY COUNT(hit.ip) DESC")
    List<DtoHitOut> getHitsWithAllIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "SELECT new ru.practicum.ewm.stat_svc.dto.model.DtoHitOut(hit.app, hit.uri, COUNT(DISTINCT hit.ip)) " +
            "FROM HitEntity AS hit " +
            "WHERE hit.timestamp BETWEEN :start AND :end " +
            "AND COALESCE(:uris, NULL) IS NULL OR hit.uri IN :uris " +
            "GROUP BY hit.app, hit.uri " +
            "ORDER BY COUNT(DISTINCT hit.ip) DESC")
    List<DtoHitOut> getHitsWithUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);
}
