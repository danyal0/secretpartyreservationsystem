package headu.mpp.secretpartyreservationsystem.place;

import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyService;
import headu.mpp.secretpartyreservationsystem.reservation.Reservation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPartyController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PartyService partyService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateParty() throws Exception {
        Party party = new Party();
        party.setId(1L);
        party.setName("Vegans Party");
        party.setCapacity(1L);
        party.setDress_code("Sexy");
        party.setPlace_id(1L);
        party.setUser_id(1L);
        Mockito.when(partyService.createParty(ArgumentMatchers.any())).thenReturn(party);
        String json = mapper.writeValueAsString(party);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/place/party").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Vegans Party")))
                .andExpect(jsonPath("$.capacity", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.dress_code", Matchers.equalTo("Sexy")))
                .andExpect(jsonPath("$.place_id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.user_id", Matchers.equalTo(1)))
        ;
    }
    @Test
    public void testViewParties() throws Exception {
        List<Party> partyList = new ArrayList<>();
        Party party = new Party();
        party.setId(1L);
        party.setName("Vegans Party");
        party.setCapacity(1L);
        party.setDress_code("Sexy");
        party.setPlace_id(1L);
        party.setUser_id(1L);
        partyList.add(party);
        Mockito.when(partyService.viewParties()).thenReturn(partyList);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(get("/api/place/party").header("Authorization", "Bearer " + token)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Vegans Party")))
                .andExpect(jsonPath("$[0].capacity", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].dress_code", Matchers.equalTo("Sexy")))
                .andExpect(jsonPath("$[0].place_id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].user_id", Matchers.equalTo(1)))
        ;
    }

    @Test
    public void testViewParty() throws Exception {
        Party party = new Party();
        party.setId(1L);
        party.setName("Vegans Party");
        party.setCapacity(1L);
        party.setDress_code("Sexy");
        party.setPlace_id(1L);
        party.setUser_id(1L);
        Mockito.when(partyService.viewParty(1L)).thenReturn(party);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(get("/api/place/party/1").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Vegans Party")))
                .andExpect(jsonPath("$.capacity", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.dress_code", Matchers.equalTo("Sexy")))
                .andExpect(jsonPath("$.place_id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.user_id", Matchers.equalTo(1)));
    }

}
