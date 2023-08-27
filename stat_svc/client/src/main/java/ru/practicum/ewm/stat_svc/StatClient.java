package ru.practicum.ewm.stat_svc;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.stat_svc.other.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.other.util.LdtCoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StatClient extends BaseClient {
    @Autowired
    private LdtCoder ldtCoder;

    @Autowired
    public StatClient(@Value("${stat.server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build());
    }

    public void saveHit(@Value("${stat.server.path}") String serverPath, DtoHitIn hitIn) {
        post(serverPath, null, null, hitIn);
    }

    public ResponseEntity<Object> getHitsWithEncodedLocalDateTime(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        Map<String, Object> paramsMap = Map.of(
                "start", ldtCoder.ldt2encodedString(start),
                "end", ldtCoder.ldt2encodedString(end),
                "uris", String.join(",", uris),
                "unique", unique
        );
        return get("${stat.server.path}" + "?start={start}&end={end}&uris={uris}&unique={unique}", null, paramsMap);
    }
}
