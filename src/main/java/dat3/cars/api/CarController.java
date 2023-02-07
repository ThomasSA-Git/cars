package dat3.cars.api;

import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.service.CarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  //ALL
  public List<CarResponse> getAll(){carService.;}
}
