package ru.practicum.ewm.stat_svc.other.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LdtCoder {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(AppConfig.DATE_TIME_FORMAT);

    public String ldt2encodedString(LocalDateTime localDateTime) {
        return URLEncoder.encode(localDateTime.format(dateTimeFormatter), StandardCharsets.UTF_8);
    }

    public LocalDateTime encodedString2ldt(String string) {
        return LocalDateTime.parse(URLDecoder.decode(string, StandardCharsets.UTF_8), dateTimeFormatter);
    }

}
