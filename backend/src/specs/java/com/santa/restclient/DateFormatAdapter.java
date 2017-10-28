package com.santa.restclient;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter dateFormat;

    public DateFormatAdapter() {
        dateFormat = DateTimeFormatter.ISO_DATE_TIME;
    }

    @Override
    public synchronized JsonElement serialize(LocalDateTime date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(date.format(dateFormat));
    }

    @Override
    public synchronized LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return LocalDateTime.parse(jsonElement.getAsString());
    }
}