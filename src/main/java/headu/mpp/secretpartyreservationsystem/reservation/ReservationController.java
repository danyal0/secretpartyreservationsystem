package headu.mpp.secretpartyreservationsystem.reservation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/party")
@RequiredArgsConstructor
public class ReservationController implements Serializable {
    public final  ReservationService reservationService;
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> makeReservation (@RequestBody ReservationCreationRequest request) {
        return ResponseEntity.ok(reservationService.makeReservation(request));
    }
    @GetMapping("/reservation/accept/{reservationId}")
    public ResponseEntity<Reservation> acceptReservation (@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.acceptRequestReservation(reservationId));
    }
    @GetMapping("/reservation/reject/{reservationId}")
    public ResponseEntity<Reservation> rejectReservation (@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.rejectRequestReservation(reservationId));
    }
    @GetMapping("/reservation/allbyhost")
    public ResponseEntity<List<Reservation>> viewReservationByHostId () {
        List<Reservation> reservations = reservationService.allReservationsByHostId();
        return ResponseEntity.ok(reservations);
    }
    @GetMapping("/reservation/byparty/{partyId}")
    public ResponseEntity<List<Reservation>> viewReservationByPartyId (@PathVariable Long partyId) {
        List<Reservation> reservations = reservationService.allReservationsByPartyId(partyId);
        return ResponseEntity.ok(reservations);
    }
    @GetMapping("/reservation/byguest")
    public ResponseEntity<List<Reservation>> viewReservationByGuestId () {
        List<Reservation> reservations = reservationService.allReservationsByGuestId();
        return ResponseEntity.ok(reservations);
    }
}
