package ru.practicum.ewm.stat_svc.server;

import ru.practicum.ewm.stat_svc.dto.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.model.HitsRequest;

import java.util.List;

public interface StatService {
    void saveHit(DtoHitIn dtoHitIn);

    List<DtoHitOut> getHits(HitsRequest hitsRequest);
}
