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

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", level_of_privacy=" + level_of_privacy +
                ", address_id=" + address_id +
                '}';
    }
}
