package ru.practicum.ewm.stat_svc.server;

import ru.practicum.ewm.stat_svc.dto.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.HitsRequest;

import java.util.List;

public interface StatService {
    public void saveHit(DtoHitIn dtoHitIn);

    public List<DtoHitOut> getHits(HitsRequest hitsRequest);
}
