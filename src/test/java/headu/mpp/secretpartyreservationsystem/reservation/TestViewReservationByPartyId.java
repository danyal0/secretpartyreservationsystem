package headu.mpp.secretpartyreservationsystem.reservation;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
public class TestViewReservationByPartyId {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationService reservationService;
    @Test
    public void testAllReservationsByPartyId() throws Exception {
        List<Reservation> reservationList = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setGuestId(1L);
        reservation.setPasscode(1L);
        reservation.setPartyId(1L);
        reservation.setStatus('W');
        reservationList.add(reservation);
        Mockito.when(reservationService.allReservationsByPartyId(reservation.getPartyId())).thenReturn(reservationList);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(get("/api/party/reservation/byparty/1").header("Authorization", "Bearer " + token)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].guestId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].passcode", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].partyId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].status", Matchers.equalTo("W")));
    }

}
