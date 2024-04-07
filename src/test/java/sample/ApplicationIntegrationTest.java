package sample;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sample.persistence.UserRepository;
import sample.persistence.model.User;
import sample.service.RestService;

@WebMvcTest(RestService.class)
public class ApplicationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testGreet() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/hello/Spock")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ message: \"Hello Spock!\"}"));
    }

    @Test
    public void testIllegalArgumentExceptionMapper() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/hello/unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("This user is invalid!")));
    }

    @Test
    public void testUser() throws Exception {
        var user = new User("Jane", "Doe");
        when(userRepository.save(eq(user))).thenReturn(user);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"firstname\": \"Jane\", \"lastname\": \"Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"firstname\": \"Jane\", \"lastname\": \"Doe\"}"));
    }

    @Test
    public void testNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/unknown"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCustomErrorController() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/error"))
                .andExpect(status().isInternalServerError());
    }
}
