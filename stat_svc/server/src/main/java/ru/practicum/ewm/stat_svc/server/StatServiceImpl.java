package ru.practicum.ewm.stat_svc.server;

import ru.practicum.ewm.stat_svc.dto.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.HitsRequest;

import java.util.List;

public class StatServiceImpl implements StatService{
    @Override
    public void saveHit(DtoHitIn dtoHitIn) {

    }

    @Override
    public List<DtoHitOut> getHits(HitsRequest hitsRequest) {
        return null;
    }
}
