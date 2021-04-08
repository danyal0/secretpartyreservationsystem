package headu.mpp.secretpartyreservationsystem.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    public void setFirstName(String fn) {

        this.firstName = fn;

    }

    public void setLastName(String ln) {

        this.lastName = ln;

    }

    public void setEmail(String e) {

        this.email = e;

    }

}