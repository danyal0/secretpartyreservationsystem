package headu.mpp.secretpartyreservationsystem.party;

import headu.mpp.secretpartyreservationsystem.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PartyRepository extends JpaRepository<Party, Long> {
    public default Optional<Party> findPartyById(Long id) {
        return findById(id);
    }

    @Query("SELECT u FROM Party u WHERE u.user_id = :user_id")
    List<Party>  getPartyByUserId(@Param("user_id") Long user_id);

}
