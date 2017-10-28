package com.santa.utils;

import cucumber.api.Transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends Transformer<LocalDateTime> {

    @Override
    public LocalDateTime transform(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}


