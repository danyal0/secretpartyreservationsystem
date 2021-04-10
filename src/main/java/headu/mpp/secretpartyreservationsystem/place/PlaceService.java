package headu.mpp.secretpartyreservationsystem.place;

import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyRepository;
import headu.mpp.secretpartyreservationsystem.party.PartyCreationRequest;
import org.springframework.beans.BeanUtils;
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
    private final PlaceRepository placeRepository;

    public Place createPlace (PlaceCreationRequest request) {
        Place place = new Place();
        BeanUtils.copyProperties(request, place);
        long i = 1;
        System.out.println(placeRepository.getPlaceById(i));
        return placeRepository.save(place);
    }

    public int findPlace(Long id){
        System.out.println(placeRepository.getPlaceById(id));
        Place place1 = new Place();
        return 2;
    }



}

