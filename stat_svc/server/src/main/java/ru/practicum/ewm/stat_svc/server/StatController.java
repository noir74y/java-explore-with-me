package ru.practicum.ewm.stat_svc.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.stat_svc.dto.StatDtoReq;
import ru.practicum.ewm.stat_svc.dto.StatDtoResp;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
@Validated
public class StatController {
    @PostMapping("/hit")
    public void create(@NotNull StatDtoReq dtoReq) {
        log.info("POST /hit/ {}", dtoReq);
    }

    @GetMapping("/stats")
    public List<StatDtoResp> get(@RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                 @RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                 @RequestParam(required = false) List<String> uris,
                                 @RequestParam(required = false) Boolean unique
    ) {
        log.info("GET /stats/");
        return List.of(StatDtoResp.builder().app("app").uri("uri").hits(0).build());
    }
}
