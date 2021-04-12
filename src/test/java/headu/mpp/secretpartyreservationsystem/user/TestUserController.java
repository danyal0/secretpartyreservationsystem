package headu.mpp.secretpartyreservationsystem.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
@SpringBootTest
@AutoConfigureMockMvc

public class TestUserController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateUser() throws Exception {
        ApiUser apiUser = new ApiUser();
        apiUser.setId(555L);
        apiUser.setUsername("MamboNo5");
        apiUser.setPassword("asdf");
        apiUser.setRole("USER");
        apiUser.setEmail("Mambo@No5.com");
        Date dob = new Date(1990, 11, 21);
        //apiUser.setDob(dob);
        apiUser.setAddress_id(3L);
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(apiUser);
        String json = mapper.writeValueAsString(apiUser);
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/user")
                //.header("")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(555)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("MamboNo5")))
                .andExpect(jsonPath("$.password", Matchers.equalTo("asdf")))
                .andExpect(jsonPath("$.role", Matchers.equalTo("USER")))
                .andExpect(jsonPath("$.email", Matchers.equalTo("Mambo@No5.com")))
                //.andExpect(jsonPath("$.dob", Matchers.equalTo("1990-11-21")))
                .andExpect(jsonPath("$.address_id", Matchers.equalTo(3)));
    }

}
