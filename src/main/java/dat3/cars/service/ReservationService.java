package dat3.cars.service;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReservationService {

  ReservationRepository reservationRepository;
  CarRepository carRepository;
  MemberRepository memberRepository;

  public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
    this.reservationRepository = reservationRepository;
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
  }

  public List<ReservationResponse> getReservations(){
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponse> reservationResponses = reservations.stream().map(r -> new ReservationResponse(r)).toList();
    return  reservationResponses;
  }

  public ReservationResponse addReservation(ReservationRequest reservationRequest){

    if (reservationRepository.existsByRentalDateAndCar(reservationRequest.getRentalDate(), reservationRequest.getCarId())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already has reservation on this date");
    }

    Car car = carRepository.findById(reservationRequest.getCarId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
    Member member = memberRepository.findById(reservationRequest.getUsername())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this username does not exist"));

    if (reservationRequest.getRentalDate().isBefore(LocalDate.now())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation date cannot be a date in the past");
    }

    Reservation makeReservation= new Reservation(member,car,reservationRequest.getRentalDate());
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

/*  public ResponseEntity<Boolean> editReservation(ReservationRequest body, int id) {
    Reservation toEdit = reservationRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation with this ID does not exist"));
//Skal finde ud af hvordan toEdit skal defineres
    Optional.ofNullable(body.getUsername()).ifPresent(toEdit::);
    Optional.ofNullable(body.getCarId()).ifPresent(toEdit::setCarId);

    reservationRepository.save(toEdit);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }*/
  }

