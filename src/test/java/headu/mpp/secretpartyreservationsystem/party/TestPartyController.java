package headu.mpp.secretpartyreservationsystem.party;

import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.place.Place;
import headu.mpp.secretpartyreservationsystem.place.PlaceService;
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
    public void testPostExample() throws Exception {
        Date partyDate = new Date();

        Party party = new Party();
        party.setId((long)3);
        party.setName("Thanksgiving");
        party.setParty_date(partyDate);
        party.setCapacity((long) 20);
        party.setDress_code("Casual");

        Mockito.when(partyService.createParty(ArgumentMatchers.any())).thenReturn(party);
        String json = mapper.writeValueAsString(party);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTkwNjcxNTV9.9bpZscwsAz7jAegVQuYgE3CS4R0mpTyCky2pEEppN2LLQU1aPRwUOaK3n2is_m55ADDrxdNGUdNVGhhFXYVisw";
        mockMvc.perform(post("/api/place/party/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(3)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Thanksgiving")))
//                .andExpect(jsonPath("$.party_date", Matchers.equalTo(partyDate)))
                .andExpect(jsonPath("$.capacity", Matchers.equalTo(20)))
                .andExpect(jsonPath("$.dress_code", Matchers.equalTo("Casual")));
    }

}
