package headu.mpp.secretpartyreservationsystem.party;

import lombok.Data;

import java.util.Date;

@Data
public class PartyCreationRequest {
    private Long id;
    private Long capacity;
    private Long place_id;
    private String dress_code;
    private String name;
    private Date party_date;
    private Long user_id;
}
