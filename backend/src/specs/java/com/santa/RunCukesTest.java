package com.santa;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(SpringProfileCucumber.class)
@CucumberOptions(features = "src/specs/resources/specs", tags = {"~@ignore"}, strict = true)
public class RunCukesTest {
}