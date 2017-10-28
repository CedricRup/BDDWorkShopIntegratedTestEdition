package com.santa.utils;

import com.santa.time.Clock;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class CukeClock implements Clock {
    private OffsetDateTime maintenant;

    public CukeClock() {
        maintenant = OffsetDateTime.now(ZoneOffset.UTC);
    }

    @Override
    public OffsetDateTime now() {
        return maintenant;
    }

    public void setTime(OffsetDateTime maintenant) {
        this.maintenant = maintenant;
    }
}
