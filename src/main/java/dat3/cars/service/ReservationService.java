package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

  ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public List<ReservationResponse> getReservations(){
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponse> reservationResponses = reservations.stream().map(r -> new ReservationResponse(r)).toList();
    return  reservationResponses;
  }

  public ReservationResponse addReservation(ReservationRequest reservationRequest){
    if (reservationRepository.existsByCar(reservationRequest.getCar())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already added to reservation");
    }
    Reservation makeReservation = ReservationRequest.getReservationEntity(reservationRequest);
    makeReservation = reservationRepository.save(makeReservation);
    return new ReservationResponse(makeReservation);
  }

  public ReservationResponse findReservationById(int id){
    Reservation foundReservation = reservationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with this ID does not exist"));;
    return new ReservationResponse(foundReservation);
  }

  public ResponseEntity<Boolean> deleteReservation(int id){
    try {
      if (reservationRepository.existsById(id)) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete reservation, it is probably 'Active'");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete reservation, it is probably 'Active'");
    }
  }

  public ResponseEntity<Boolean> editReservation(ReservationRequest body, int id) {
    Reservation toEdit = reservationRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation with this ID does not exist"));

    Optional.ofNullable(body.getMember()).ifPresent(toEdit::setMember);
    Optional.ofNullable(body.getCar()).ifPresent(toEdit::setCar);

    reservationRepository.save(toEdit);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
/*    public void updateCar ( int id, int carId){
    Reservation updatedReservation = reservationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with this ID does not exist"));
    updatedReservation.setCar();
    reservationRepository.save(updatedReservation);
    }*/
/*
  public void updateRanking(String userName, int ranking) {
    Member updatedMember = memberRepository.findById(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));

    updatedMember.setRanking(ranking);
    memberRepository.save(updatedMember);
  }

  }*/
  }

