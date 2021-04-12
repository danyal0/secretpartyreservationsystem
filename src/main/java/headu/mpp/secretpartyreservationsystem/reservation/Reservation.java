package headu.mpp.secretpartyreservationsystem.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long guestId;
    private Character status;
    private Long partyId;
    private Long passcode;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", guestId=" + guestId +
                ", status=" + status +
                ", partyId=" + partyId +
                ", passcode=" + passcode +
                '}';
    }
}
