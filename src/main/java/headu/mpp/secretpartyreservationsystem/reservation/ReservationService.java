package headu.mpp.secretpartyreservationsystem.reservation;

import headu.mpp.secretpartyreservationsystem.party.Party;
import headu.mpp.secretpartyreservationsystem.party.PartyCreationRequest;
import headu.mpp.secretpartyreservationsystem.party.PartyRepository;
import headu.mpp.secretpartyreservationsystem.place.Place;
import headu.mpp.secretpartyreservationsystem.user.UserRepository;
import headu.mpp.secretpartyreservationsystem.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    public Reservation makeReservation(ReservationCreationRequest reservation) {
        Optional<Party> party = null;
        try{
            party = partyRepository.findPartyById(reservation.getPartyId());
        }catch (NullPointerException e){
            System.out.println("Message: " + e);
        }

        if (!party.isPresent()) {
            throw new EntityNotFoundException("Party Not Found");
        }
        Reservation reservationToCreate = new Reservation();
        BeanUtils.copyProperties(reservation, reservationToCreate);
        reservationToCreate.setPartyId(party.get().getId());
        Random r = new Random();
        Long code = ((Integer) r.nextInt(1000000)).longValue();
        reservationToCreate.setPasscode(code);
        reservationToCreate.setGuestId(userRepository.getCurUser());

        return reservationRepository.save(reservationToCreate);
    }
    public Reservation acceptRequestReservation(long id) {
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setStatus('A');
        return reservationRepository.save(reservation);
    }
    public Reservation rejectRequestReservation(long id) {
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setStatus('R');
        return reservationRepository.save(reservation);
    }
    public List<Reservation> allReservationsByHostId() {
       List<Party> listparties =  partyRepository.getPartyByUserId(userRepository.getCurUser());
       List<Reservation> reservationList = new ArrayList<Reservation>();
       for(Party party: listparties){
           reservationList.addAll(reservationRepository.getReservationByPartyId(party.getId()));
       }
        return reservationList;
    }
    public List<Reservation> allReservationsByPartyId(Long partyId) {
        List<Reservation> reservationList = reservationRepository.getReservationByPartyId(partyId);
        return reservationList;
    }
    public List<Reservation> allReservationsByGuestId() {
        List<Reservation> reservationGuestList = reservationRepository.getReservationByGuestId(userRepository.getCurUser());
        return reservationGuestList;
    }
}
