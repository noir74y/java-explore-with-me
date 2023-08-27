package ru.practicum.ewm.stat_svc.dto.model;

public interface DtoHitOutView {
    String getApp();

    String getUri();

    Integer getHits();

    default DtoHitOut getDtoHitOut() {
        return DtoHitOut.builder()
                .app(getApp())
                .uri(getUri())
                .hits(getHits())
                .build();
    }
}
