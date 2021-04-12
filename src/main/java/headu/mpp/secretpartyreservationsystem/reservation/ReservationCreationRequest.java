package headu.mpp.secretpartyreservationsystem.reservation;

import lombok.Data;

@Data
public class ReservationCreationRequest {
    private Long id;
    private Long guestId;
    private Character status;
    private Long partyId;
    private Long passcode;
}
