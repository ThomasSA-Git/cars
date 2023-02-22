package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationServiceTest {


  @Autowired
  private CarRepository carRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  ReservationService reservationService;

  private boolean dataIsInitialized = false;


  @BeforeEach
  public void setupData() {
    reservationService = new ReservationService(reservationRepository, carRepository, memberRepository);
    if (!dataIsInitialized) {
      List<Member> members = List.of(
          new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800"),
          new Member("m2", "mm@a.dk", "pw", "bb", "bbb", "bbbb", "bbbb", "1234"));
      List<Car> cars = List.of(
          Car.builder().brand("Ford").model("Focus").pricePrDay(1250).bestDiscount(20).build(),
          Car.builder().brand("Ford").model("Fiesta").pricePrDay(1500).bestDiscount(20).build());

      memberRepository.saveAllAndFlush(members);
      carRepository.saveAllAndFlush(cars);
      Reservation reservation = new Reservation(memberRepository.findMemberByUsername("m1"), carRepository.findCarById(1), LocalDate.of(2023, 11, 11));
      reservationRepository.saveAndFlush(reservation);
      dataIsInitialized = true;
    }
  }
/*
  @Test
  void addReservation() {
    Reservation reservation = new Reservation(memberRepository.findMemberByUsername("m2"), carRepository.findCarById(2), LocalDate.of(2023, 11, 11));
    ReservationRequest reservationRequest = new ReservationRequest(reservation);
    ReservationResponse newResponse = reservationService.addReservation(reservationRequest);
    assertEquals("m2", newResponse.getUsername());
    assertEquals("Fiesta", newResponse.getModel());
    assertNotNull(newResponse.getId());
  }*/
/*
  @Test
  void addReservationException() {
    Reservation reservation = new Reservation(memberRepository.findMemberByUsername("m2"), carRepository.findCarById(1), LocalDate.of(2023, 11, 11));
    ReservationRequest request = new ReservationRequest(reservation);

    Throwable exception = assertThrows(ResponseStatusException.class, () ->
        reservationService.addReservation(request));
    String expectedMessage = "Car already has reservation on this date";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  void findReservationById() {
    ReservationResponse response = reservationService.findReservationById(1);
    assertEquals("Focus", response.getModel());
    assertEquals("m1", response.getUsername());
  }

  //Test af uge3 opgave 8B
  @Test
  void getReservationsByMember() {
  List<Reservation> reservations = reservationRepository.findByMember_username("m1");
  List<ReservationResponse> reservationResponses = reservations.stream().map(r -> new ReservationResponse(r)).toList();
  assertEquals(1, reservationResponses.size());
}*/
}