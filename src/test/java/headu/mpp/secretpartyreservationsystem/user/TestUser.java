package headu.mpp.secretpartyreservationsystem.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.place.Place;
import headu.mpp.secretpartyreservationsystem.place.PlaceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestUser {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateUser() throws Exception {
        ApiUser user = new ApiUser();
        user.setId(1L);
        user.setUsername("Juanca");
        user.setPassword("asdf");
        user.setRole("USER");
        Date dob = new Date(2000, 11, 21);
        user.setDob(dob);
        user.setEmail("juan@camilo.com");
        user.setAddress_id(1L);
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(user);
        String json = mapper.writeValueAsString(user);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/user/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.username", Matchers.equalTo("Arun")))
                .andExpect(jsonPath("$.role", Matchers.equalTo("abc")))
                .andExpect(jsonPath("$..me quiero de, Matchers.equalTo(1)))
                .andExpect(jsonPath("$.level_of_privacy", Matchers.equalTo(1)));
    }

    private void thenReturn(ApiUser user) {
    }

}
