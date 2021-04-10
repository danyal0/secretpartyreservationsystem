package headu.mpp.secretpartyreservationsystem.place;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT u FROM Place u WHERE u.id = :id")
    Place getPlaceById(@Param("id") Long id);

    public default Optional<Place> findPlaceById(Long id) {
        return findById(id);
    }
}
