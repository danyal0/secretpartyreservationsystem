package headu.mpp.secretpartyreservationsystem.party;

import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPartyController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartyService partyService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReservationExample() throws Exception {
        Party party = new Party();
        party.setId((long) 3);
        party.setName("test party");
        party.setParty_date(new Date());
        party.setDress_code("white");
        party.setCapacity((long) 10);
        party.setPlace_id((long) 1);
        party.setUser_id((long) 1);

        Mockito.when(partyService.createParty(ArgumentMatchers.any())).thenReturn(party);
        String json = mapper.writeValueAsString(party);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/place/party/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("test party")))
                .andExpect(jsonPath("$.dress_code", Matchers.equalTo("white")))
                .andExpect(jsonPath("$.capacity", Matchers.equalTo(10)))
                .andExpect(jsonPath("$.place_id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.user_id", Matchers.equalTo(1)));
    }
}
