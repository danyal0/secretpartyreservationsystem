package headu.mpp.secretpartyreservationsystem.reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.reservation.ReservationService;
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
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRejectReservation {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationService reservationService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRejectReservation() throws Exception {


        Reservation reservation = new Reservation();
        reservation.setId(2L);
        reservation.setGuestId(1L);
        reservation.setPartyId(1L);
        reservation.setStatus('R');
        reservation.setPasscode(435795L);
        Mockito.when(reservationService.rejectRequestReservation(reservation.getId())).thenReturn(reservation);
        String json = mapper.writeValueAsString(reservation);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";

        mockMvc.perform(get("/api/party/reservation/reject/2")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.guestId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.partyId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.status", Matchers.equalTo("R")))
                .andExpect(jsonPath("$.passcode", Matchers.equalTo(435795)));




    }


}
