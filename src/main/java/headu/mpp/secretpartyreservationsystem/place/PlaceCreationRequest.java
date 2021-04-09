package headu.mpp.secretpartyreservationsystem.place;

import lombok.Data;
@Data
public class PlaceCreationRequest {
    private Long id;
    private String code;
    private String name;
    private Integer level_of_privacy;
    private Long address_id;
}