package com.santa.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController()
@RequestMapping("/time")
public class TimeController {

    private Clock clock;

    @Autowired
    public TimeController(Clock clock) {
        this.clock = clock;
    }


    @GetMapping
    public OffsetDateTime sayTime(){
        return clock.now();
    }
}
