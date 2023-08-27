package ru.practicum.ewm.stat_svc.server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOutView;
import ru.practicum.ewm.stat_svc.dto.model.HitsRequest;
import ru.practicum.ewm.stat_svc.dto.utils.mapper.HitModelMapper;

import java.util.List;
import java.util.stream.Collectors;

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
        var hitOutViewList = hitsRequest.getUnique() ?
                statRepository.getHitsWithUniqueIp(hitsRequest.getStart(), hitsRequest.getEnd(), hitsRequest.getUris()) :
                statRepository.getHitsWithAllIp(hitsRequest.getStart(), hitsRequest.getEnd(), hitsRequest.getUris());
        return hitOutViewList.stream().map(DtoHitOutView::getDtoHitOut).collect(Collectors.toList());
    }
}