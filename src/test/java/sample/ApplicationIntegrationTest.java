package sample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sample.persistence.UserRepository;
import sample.service.RestService;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by markus on 14/05/16.
 */
@WebMvcTest(RestService.class)
public class ApplicationIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationIntegrationTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGreet() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/service/hello/Spock")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ message: \"Hello Spock!\"}"));
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