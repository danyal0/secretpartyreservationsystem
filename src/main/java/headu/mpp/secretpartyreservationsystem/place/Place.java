package headu.mpp.secretpartyreservationsystem.place;

import com.fasterxml.jackson.annotation.JsonBackReference;
import headu.mpp.secretpartyreservationsystem.party.Party;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "places")
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Integer level_of_privacy;
    private Long address_id;

//    @JsonBackReference
//    @OneToMany(mappedBy = "places",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Party> partyList;
}
