package com.santa.utils;

import cucumber.api.Transformer;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConverter extends Transformer<OffsetDateTime> {

    @Override
    public OffsetDateTime transform(String value) {
        LocalDateTime local = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return OffsetDateTime.of(local, ZoneOffset.UTC);
    }
}


