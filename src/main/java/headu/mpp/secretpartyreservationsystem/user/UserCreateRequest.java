package headu.mpp.secretpartyreservationsystem.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserCreateRequest {
    private String id;
    private String username;
    private String password;
    private String role;
    private Date dob;
    private String email;
    private Long address_id;
}
