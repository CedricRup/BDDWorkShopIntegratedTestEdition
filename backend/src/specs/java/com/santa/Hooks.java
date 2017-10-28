package com.santa;

import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Hooks {


    @Autowired
    public Hooks() {
    }

    @Before
    public void beforeScenario() throws IOException {
    }


}
