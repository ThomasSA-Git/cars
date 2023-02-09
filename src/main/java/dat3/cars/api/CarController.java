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
  @GetMapping(path = "/{id}")
  CarResponse getCarById(@PathVariable int id){return carService.findCarById(id);}

  //ADMIN
  @PostMapping()
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  //MEMBER
  @PutMapping("/{id}")
  ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
    return carService.updateCar(body, id);
  }

  //ADMIN
  @PatchMapping("/pricePrDay/{id}/{value}")
  void setPricePrDay(@PathVariable int id, @PathVariable double value) {
    carService.updatePricePrDay(id, value);
  }

  //ADMIN
  @DeleteMapping("/{id}")
  void deleteCarById(@PathVariable int id) {
    carService.deleteCar(id);
  }
}
