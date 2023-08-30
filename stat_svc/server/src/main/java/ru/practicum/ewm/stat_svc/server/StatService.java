package ru.practicum.ewm.stat_svc.server;

import ru.practicum.ewm.stat_svc.other.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.other.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.other.model.HitsRequest;

import java.util.List;

public interface StatService {
    void saveHit(DtoHitIn dtoHitIn);

    List<DtoHitOut> getHits(HitsRequest hitsRequest);
}
