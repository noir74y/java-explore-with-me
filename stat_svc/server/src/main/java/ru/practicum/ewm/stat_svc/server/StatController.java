package ru.practicum.ewm.stat_svc.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.stat_svc.other.error.exception.CustomValidationException;
import ru.practicum.ewm.stat_svc.other.model.DtoHitIn;
import ru.practicum.ewm.stat_svc.other.model.DtoHitOut;
import ru.practicum.ewm.stat_svc.other.model.HitsRequest;
import ru.practicum.ewm.stat_svc.other.util.LdtCoder;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatController {
    final StatService statService;
    final Validator hitRequestValidator;
    final LdtCoder ldtCoder;

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
                .start(ldtCoder.encodedString2ldt(start))
                .end(ldtCoder.encodedString2ldt(end))
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
        return getHitsWithEncodedLocalDateTime
                (
                        ldtCoder.ldt2encodedString(start),
                        ldtCoder.ldt2encodedString(end),
                        uris,
                        unique
                );
    }
}
