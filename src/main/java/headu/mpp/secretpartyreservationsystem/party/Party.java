package headu.mpp.secretpartyreservationsystem.party;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import headu.mpp.secretpartyreservationsystem.place.Place;
import headu.mpp.secretpartyreservationsystem.user.ApiUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "parties")
@NoArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long capacity;
    private Long place_id;
    private String dress_code;
    private String name;
    private Date party_date;
    private Long user_id;


//    @ManyToOne
//    @JoinColumn(name = "place_id")
//    @JsonManagedReference
//    private Place place;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonManagedReference
//    private ApiUser apiUser;

}
