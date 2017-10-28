package com.santa.restclient;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeFormatAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    private final DateTimeFormatter dateFormat;

    public OffsetDateTimeFormatAdapter() {
        dateFormat = DateTimeFormatter.ISO_DATE_TIME;
    }

    @Override
    public synchronized JsonElement serialize(OffsetDateTime date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(date.format(dateFormat));
    }

    @Override
    public synchronized OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return OffsetDateTime.parse(jsonElement.getAsString());
    }
}