package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sample.service.RestService;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by markus on 14/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationIntegrationTest {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationIntegrationTest.class);

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new RestService()).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGreet() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/service/hello/Spock")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello Spock!")));
    }

    @Test
    public void testIllegalArgumentExceptionMapper() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/service/hello/unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("This user is invalid!")));
    }

    @Test
    public void testNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/unknown"))
                .andExpect(status().isNotFound());
    }
}