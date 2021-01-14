package sample.service.model;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by markus on 11/06/16.
 */
public class GreetingModelTest {

    @Test
    public void testGreet() {
        Greeting greeting = new Greeting("Spock");
        assertThat(greeting.getMessage(), equalTo("Hello Spock!"));
    }

}
