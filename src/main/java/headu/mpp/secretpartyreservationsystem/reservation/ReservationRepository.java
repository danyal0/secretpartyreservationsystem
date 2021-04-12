package headu.mpp.secretpartyreservationsystem.reservation;

import headu.mpp.secretpartyreservationsystem.party.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation , Long> {

    @Query("SELECT u FROM Reservation u WHERE u.partyId = :party_id")
    List<Reservation>  getReservationByPartyId(@Param("party_id") Long party_id);

    @Query("SELECT u FROM Reservation u WHERE u.guestId = :guest_id")
    List<Reservation>  getReservationByGuestId(@Param("guest_id") Long guest_id);
}
