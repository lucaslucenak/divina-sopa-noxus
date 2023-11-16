package com.lucalucenak.Noxus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Configuration
public class DateTimeConfig {

    @Bean
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    @Bean
    public ZoneId zoneId() {
        return ZoneId.of("GMT-3");
    }
}
