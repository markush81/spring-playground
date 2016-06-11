package sample.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by markus on 11/06/16.
 */
public class RestServiceTest {

    private RestService restService;

    @Before
    public void setUp() {
        restService = new RestService();
    }

    @Test
    public void testGreet() {
        String greeting = restService.greet("Spock");
        assertThat(greeting, equalTo("Hello Spock!"));
    }

}
