package sample.service.model;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class GreetingModelTest {

    @Test
    public void testGreet() {
        Greeting greeting = new Greeting("Spock");
        assertThat(greeting.getMessage(), equalTo("Hello Spock!"));
    }

}
