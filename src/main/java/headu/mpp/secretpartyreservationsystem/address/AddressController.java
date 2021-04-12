package headu.mpp.secretpartyreservationsystem.address;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value ="/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress (@RequestBody AddressCreationRequest request) {
        return ResponseEntity.ok(addressService.createAddress(request));
    }

}
