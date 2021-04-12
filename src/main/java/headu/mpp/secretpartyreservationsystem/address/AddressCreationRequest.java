package headu.mpp.secretpartyreservationsystem.address;

import lombok.Data;

@Data
public class AddressCreationRequest {
    private Long id;
    private String street;
    private String city;
    private String state;
    private Integer zip;
}
