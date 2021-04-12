package headu.mpp.secretpartyreservationsystem.place;

import org.springframework.http.HttpStatus;
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

    @PostMapping("/")
    public ResponseEntity<Place> createPlace (@RequestBody PlaceCreationRequest request) {
        return new ResponseEntity<>(placeService.createPlace(request), HttpStatus.CREATED);
    }

}

