package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by markus on 14/05/16.
 */
public class MainIntegrationTest {

    private static Logger LOGGER = LoggerFactory.getLogger(MainIntegrationTest.class);

    private Main underTest;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        underTest = new Main();

        // Just as an example what could be added in integration test
        Class.forName("org.h2.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void database() throws Exception {
        //stupid test of course, just serves as an example
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        assertThat(resultSet, is(notNullValue()));
        LOGGER.trace("ResultSet: {}", resultSet);
    }

    @Test
    public void greet() throws Exception {
        assertThat(underTest.greet(), startsWith("Hello"));
    }

}