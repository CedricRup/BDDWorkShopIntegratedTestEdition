package com.santa.steps;

import com.santa.restclient.IRestClient;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.fr.Etantdonné;
import com.santa.SantaApplication;
import com.santa.time.Clock;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Response;
import com.santa.utils.CukeClock;
import com.santa.ConfigurationTest;
import com.santa.utils.OffsetDateTimeConverter;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = ConfigurationTest.class)
@SpringBootTest(classes = SantaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:test.properties")
public class TimeSteps {

    private Clock clock;
    Response<OffsetDateTime> timeResponse;
    IRestClient user;

    public TimeSteps(Clock clock, World world) {
        this.clock = clock;
        user = world.getUserConnecte().getRestClient();
    }


    @Given("^the clock is set to (.+)$")
    public void the_clock_is_set_to(@Transform(OffsetDateTimeConverter.class) OffsetDateTime date) throws Throwable {
        ((CukeClock)clock).setTime(date);
    }

    @When("^the time is requested$")
    public void the_time_is_requested() throws Throwable {
        timeResponse = user.whatTimeisIt().execute();
    }

    @Then("^the system answer with the time (.+)$")
    public void the_time_is(@Transform(OffsetDateTimeConverter.class) OffsetDateTime date) throws Throwable {
        assertEquals(date,timeResponse.body());
    }
}
