package headu.mpp.secretpartyreservationsystem.place;

import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyCreationRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/party")
    public ResponseEntity viewParties() {
            return ResponseEntity.ok(placeService.viewParties());
    }

    @GetMapping("/party/{partyId}")
    public ResponseEntity<Party> viewParty (@PathVariable Long partyId) {
        return ResponseEntity.ok(placeService.viewParty(partyId));
    }

    @PostMapping("/party")
    public ResponseEntity<Party> createParty (@RequestBody PartyCreationRequest request) {
        return ResponseEntity.ok(placeService.createParty(request));
    }

    @DeleteMapping("/party/{partyId}")
    public ResponseEntity<Void> deleteParty (@PathVariable Long partyId) {
        placeService.deleteParty(partyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<Place> createPlace (@RequestBody PlaceCreationRequest request) {
        return ResponseEntity.ok(placeService.createPlace(request));
    }

}

