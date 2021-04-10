package headu.mpp.secretpartyreservationsystem.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import headu.mpp.secretpartyreservationsystem.party.Party;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class ApiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private Date dob;
    private String email;
    private Long address_id;

//    @JsonBackReference
//    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Party> parties;

}
