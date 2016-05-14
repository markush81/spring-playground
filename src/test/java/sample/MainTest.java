package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

/**
 * Created by markus on 14/05/16.
 */
public class MainTest {

    private Main underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new Main();
    }

    @After
    public void tearDown() throws Exception {
        // currently nothing^
    }

    @Test
    public void greet() throws Exception {
        assertThat(underTest.greet(), startsWith("Hello"));
    }

}