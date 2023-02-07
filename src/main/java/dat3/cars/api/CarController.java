package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  //ALL
  @GetMapping
  List<CarResponse> getCars(){return carService.getCars(false);}

  //ALL
  @GetMapping(path = "/{username}")
  CarResponse getCarById(){return null;}

  //ADMIN
  @PostMapping()
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  //MEMBER
  @PutMapping("/{id}")
  ResponseEntity<Boolean> editCar(@RequestBody MemberRequest body, @PathVariable String id){
    return null;
  }

  //ADMIN
  @PatchMapping("/pricePrDay/{id}/{value}")
  void setPricePrDay(@PathVariable String id, @PathVariable double value) {}

  //ADMIN
  @DeleteMapping("/{id}")
  void deleteCarById(@PathVariable int id) {}
}
