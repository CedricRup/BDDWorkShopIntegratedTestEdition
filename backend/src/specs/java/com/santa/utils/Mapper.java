package com.santa.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Mapper {

    private Map<String, String> map;

    public Mapper(Map<String, String> map) {
        this.map = map;
    }

    public void setString(String key, Consumer<String> setter) {
        if (map.containsKey(key)) {
            setter.accept(map.get(key));
        }
    }

    public void setLocalDateTime(String key, Consumer<LocalDateTime> setter) {
        setFromMap(key, this::parseLocalDateTime, setter);
    }

    public void setOffsetDateTime(String key, Consumer<OffsetDateTime> setter) {
        setFromMap(key, this::parseOffsetDateTime, setter);
    }

    public void setInteger(String key, Consumer<Integer> setter) {
        setFromMap(key, this::parseInteger, setter);
    }


    public void setFloat(String key, Consumer<Float> setter) {
        setFromMap(key, this::parseFloat, setter);
    }

    public Float parseFloat(String s) {
        return Float.valueOf(s);
    }

    public Boolean parseBoolean(String b) {
        return Boolean.valueOf(b);
    }

    public Integer parseInteger(String i) {
        return Integer.valueOf(i);
    }


    public void setBoolean(String key, Consumer<Boolean> setter) {
        setFromMap(key, this::parseBoolean, setter);
    }

    private <T> void setFromMap(String key, Function<String, T> converter, Consumer<T> setter) {
        if (map.containsKey(key)) {
            setter.accept(converter.apply(map.get(key)));
        }
    }

    private LocalDateTime parseLocalDateTime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    private OffsetDateTime parseOffsetDateTime(String s) {
        LocalDateTime date = parseLocalDateTime(s);
        return OffsetDateTime.of(date, ZoneOffset.UTC);
    }

    public void setDouble(String key, Consumer<Double> setter) {
        setFromMap(key, this::parseDouble, setter);
    }

    private Double parseDouble(String d) {
        return Double.valueOf(d);
    }

}
