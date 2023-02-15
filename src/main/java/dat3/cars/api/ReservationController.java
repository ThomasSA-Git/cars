package dat3.cars.api;


import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
  private final ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }


  //ALL
  @GetMapping
  List<ReservationResponse> getReservations(){return reservationService.getReservations();}

  //ALL
  @GetMapping(path = "/{id}")
  ReservationResponse getReservationById(@PathVariable int id){return reservationService.findReservationById(id);}

  //ADMIN
  @PostMapping()
  ReservationResponse addReservation(@RequestBody ReservationRequest body){
    return reservationService.addReservation(body);
  }

  //ADMIN
  @PutMapping("/{id}")
  ResponseEntity<Boolean> editReservation(@RequestBody ReservationRequest body, @PathVariable int id){
    return reservationService.editReservation(body, id);
  }

  //ADMIN
/*  @PatchMapping("/pricePrDay/{id}/{value}")
  void setPricePrDay(@PathVariable int id, @PathVariable double value) {
    carService.updatePricePrDay(id, value);
  }

  //ADMIN
  @DeleteMapping("/{id}")
  ResponseEntity<Boolean> deleteCarById(@PathVariable int id) {
    return carService.deleteCar(id);
  }*/
}
