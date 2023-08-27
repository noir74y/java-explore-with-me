package ru.practicum.ewm.stat_svc.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.stat_svc.dto.error.exception.CustomValidationException;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.dto.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.dto.model.HitsRequest;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StatController {
    private final StatService statService;
    private final DateTimeFormatter dateTimeFormatter;
    private final Validator hitRequestValidator;

    @PostMapping("/hit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveHit(@Valid @RequestBody @NotNull DtoHitIn hitIn) {
        log.info("POST /hit {}", hitIn);
        statService.saveHit(hitIn);
    }

    @GetMapping("/stats")
    @Valid
    public List<DtoHitOut> getHitsWithEncodedLocalDateTime(@RequestParam @NotNull String start,
                                                           @RequestParam @NotNull String end,
                                                           @RequestParam(required = false) List<String> uris,
                                                           @RequestParam(defaultValue = "false") Boolean unique
    ) throws UnsupportedEncodingException {
        HitsRequest hitsRequest = HitsRequest.builder()
                .start(LocalDateTime.parse(decodeValue(start), dateTimeFormatter))
                .end(LocalDateTime.parse(decodeValue(end), dateTimeFormatter))
                .uris(uris)
                .unique(unique)
                .build();

        hitRequestValidator.validate(hitsRequest).stream().findFirst().ifPresent(constraintViolation -> {
            throw new CustomValidationException("bad request", constraintViolation.getMessage());
        });

        log.info("GET /stats {}", hitsRequest);
        return statService.getHits(hitsRequest);
    }

    @GetMapping("/stats/plainLocalDateTime")
    @Valid
    public List<DtoHitOut> getHitsWithPlainLocalDateTime(@RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                         @RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                         @RequestParam(required = false) List<String> uris,
                                                         @RequestParam(defaultValue = "false") Boolean unique
    ) throws UnsupportedEncodingException {
        log.info("GET /stats/plainLocalDateTime {}", HitsRequest.builder().start(start).end(end).uris(uris).unique(unique).build());
        return getHitsWithEncodedLocalDateTime(encodeValue(start.format(dateTimeFormatter)), encodeValue(end.format(dateTimeFormatter)), uris, unique);
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private String decodeValue(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
