package headu.mpp.secretpartyreservationsystem.address;

import headu.mpp.secretpartyreservationsystem.place.PlaceRepository;
import headu.mpp.secretpartyreservationsystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address createAddress(AddressCreationRequest request){
        Address address = new Address();
        BeanUtils.copyProperties(request, address);
        return addressRepository.save(address);
    }
}
