package headu.mpp.secretpartyreservationsystem.party;
import headu.mpp.secretpartyreservationsystem.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PartyController {
    @Autowired
    private final PlaceService placeService;

    private final PartyService partyService;

    @GetMapping("/party")
    public ResponseEntity viewParties() {
        return ResponseEntity.ok(partyService.viewParties());
    }

    @GetMapping("/party/{partyId}")
    public ResponseEntity<Party> viewParty (@PathVariable Long partyId) {
        return ResponseEntity.ok(partyService.viewParty(partyId));
    }

    @PostMapping("/party")
    public ResponseEntity<Party> createParty (@RequestBody PartyCreationRequest request) {
        return ResponseEntity.ok(partyService.createParty(request));
    }

    @DeleteMapping("/party/{partyId}")
    public ResponseEntity<Void> deleteParty (@PathVariable Long partyId) {
        partyService.deleteParty(partyId);
        return ResponseEntity.ok().build();
    }

}