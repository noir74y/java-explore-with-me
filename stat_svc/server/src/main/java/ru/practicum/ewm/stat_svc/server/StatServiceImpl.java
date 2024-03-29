package ru.practicum.ewm.stat_svc.server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.stat_svc.other.mapper.HitModelMapper;
import ru.practicum.ewm.stat_svc.other.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.other.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.other.model.HitsRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final HitModelMapper hitModelMapper;
    private final StatRepository statRepository;

    @Override
    public void saveHit(DtoHitIn hitIn) {
        statRepository.save(hitModelMapper.hitIn2hitEntity(hitIn));
    }

    @Override
    public List<DtoHitOut> getHits(HitsRequest hitsRequest) {
        return hitsRequest.getUnique() ?
                statRepository.getHitsWithUniqueIp(hitsRequest.getStart(), hitsRequest.getEnd(), hitsRequest.getUris()) :
                statRepository.getHitsWithAllIp(hitsRequest.getStart(), hitsRequest.getEnd(), hitsRequest.getUris());
    }
}
