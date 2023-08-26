package ru.practicum.ewm.stat_svc.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.stat_svc.dto.StatDtoRequest;
import ru.practicum.ewm.stat_svc.dto.StatDtoResponse;
import ru.practicum.ewm.stat_svc.dto.StatInfoRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@Validated
public class StatController {
    @PostMapping("/hit")
    public void create(@NotNull StatDtoRequest dtoReq) {
        log.info("POST /hit/ {}", dtoReq);
    }

    @GetMapping("/stats")
    public List<StatDtoResponse> get(@RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                     @RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                     @RequestParam(required = false) List<String> uris,
                                     @RequestParam(defaultValue = "false") Boolean unique
    ) {
        StatInfoRequest statInfoRequest = StatInfoRequest.builder().start(start).end(end).uris(uris).unique(unique).build();
        log.info("GET /stat {}", statInfoRequest);
        return List.of(StatDtoResponse.builder().app("").uri("").hits(0).build());
    }
}
