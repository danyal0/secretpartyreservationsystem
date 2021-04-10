package headu.mpp.secretpartyreservationsystem.place;

import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyRepository;
import headu.mpp.secretpartyreservationsystem.party.PartyCreationRequest;
import headu.mpp.secretpartyreservationsystem.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

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

    public Party createParty(PartyCreationRequest party) {
        Optional<Place> place = placeRepository.findById(party.getPlace_id());
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


    public Place createPlace (PlaceCreationRequest request) {
        Place place = new Place();
        BeanUtils.copyProperties(request, place);
        return placeRepository.save(place);
    }





}

