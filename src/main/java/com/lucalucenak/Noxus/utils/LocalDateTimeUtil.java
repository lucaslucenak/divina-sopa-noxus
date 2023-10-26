package com.lucalucenak.Noxus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class LocalDateTimeUtil {

    @Autowired
    private LocalDateTime localDateTime;
    @Autowired
    private final ZoneId zoneId = ZoneId.of("GMT-3");

    public LocalDateTimeUtil() {
    }

    public LocalDateTime nowGMT3() {
        return LocalDateTime.now(zoneId);
    }
}
