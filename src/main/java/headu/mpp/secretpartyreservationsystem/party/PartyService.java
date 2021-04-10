package headu.mpp.secretpartyreservationsystem.party;

import headu.mpp.secretpartyreservationsystem.place.Place;
import headu.mpp.secretpartyreservationsystem.place.PlaceRepository;
import headu.mpp.secretpartyreservationsystem.place.PlaceService;
import lombok.RequiredArgsConstructor;
import headu.mpp.secretpartyreservationsystem.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public Party viewParty(Long id) {
        Optional<Party> party = partyRepository.findById(id);
        if (party.isPresent()) {
            return party.get();
        }
        throw new EntityNotFoundException("Cant find any party under given ID");
    }

    public List<Party> viewParties() {
        return partyRepository.findAll();
    }

    public List<Party> viewPartiesByPlace(Long partyid, Long userid) {

        return partyRepository.findAll();
    }

    public Party createParty(PartyCreationRequest party) {
        Optional<Place> place = null;
        try{
            place = placeRepository.findPlaceById(party.getPlace_id());
        }catch (NullPointerException e){
            System.out.println("Message: " + e);
        }

        if (!place.isPresent()) {
            throw new EntityNotFoundException("Place Not Found");
        }
        Party partyToCreate = new Party();
        BeanUtils.copyProperties(party, partyToCreate);
        partyToCreate.setPlace_id(place.get().getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long curUserId = userRepository.findByUsername(currentPrincipalName).get().getId();
        partyToCreate.setUser_id(curUserId);
        return partyRepository.save(partyToCreate);
    }

    public void deleteParty(Long id) {
        partyRepository.deleteById(id);
    }
}
