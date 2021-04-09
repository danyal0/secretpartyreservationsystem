package headu.mpp.secretpartyreservationsystem.user;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
    private String role;
}
