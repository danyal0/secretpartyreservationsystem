package headu.mpp.secretpartyreservationsystem.place;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class TestPlaceController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaceService placeService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPostExample() throws Exception {
        Place place = new Place();
        place.setId(1L);
        place.setName("Arun");
        place.setCode("abc");
        place.setAddress_id(1L);
        place.setLevel_of_privacy(1);
        Mockito.when(placeService.createPlace(ArgumentMatchers.any())).thenReturn(place);
        String json = mapper.writeValueAsString(place);
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTkwNjcxNTV9.9bpZscwsAz7jAegVQuYgE3CS4R0mpTyCky2pEEppN2LLQU1aPRwUOaK3n2is_m55ADDrxdNGUdNVGhhFXYVisw";
        mockMvc.perform(post("/api/place/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("Arun")))
                .andExpect(jsonPath("$.code", Matchers.equalTo("abc")))
                .andExpect(jsonPath("$.address_id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.level_of_privacy", Matchers.equalTo(1)));
    }

}
