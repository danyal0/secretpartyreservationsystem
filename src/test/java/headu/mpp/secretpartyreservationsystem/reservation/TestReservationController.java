package headu.mpp.secretpartyreservationsystem.reservation;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestReservationController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReservationExample() throws Exception {

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setGuestId((long)1);
        reservation.setStatus('P');
        reservation.setPartyId((long) 1);
        reservation.setPasscode((long) 1);

        Mockito.when(reservationService.makeReservation(ArgumentMatchers.any())).thenReturn(reservation);
        String json = mapper.writeValueAsString(reservation);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/reservation/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1L)))
                .andExpect(jsonPath("$.guestId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.status", Matchers.equalTo('P')))
                .andExpect(jsonPath("$.partyId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.passcode", Matchers.equalTo(1)));
    }

}
