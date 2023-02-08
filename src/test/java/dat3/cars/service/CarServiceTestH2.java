package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarServiceTestH2 {


  @Autowired
  private CarRepository carRepository;


  CarService carService;

  private boolean dataIsInitialized = false;


  @BeforeEach
  public void setupData() {
    carService = new CarService(carRepository);
    if (!dataIsInitialized) {
      List<Car> cars = List.of(
          new Car("Ford", "Focus", 1250, 20),
          new Car("Ford", "Fiesta", 1500, 20)
      );
      carRepository.saveAll(cars);
      dataIsInitialized = true;
    }
  }
  @Test
  void getCars() {
    List<CarResponse> response = carService.getCars(false);
    assertEquals(2,response.size());
    assertThat(response, containsInAnyOrder(hasProperty("model", is("Focus")), hasProperty("model", is("Fiesta"))));
  }

  @Test
  void addCar() {
    Car newCar = new Car("Ford", "Mondeo", 1750, 20);
    CarRequest request = new CarRequest(newCar);
    CarResponse response = carService.addCar(request);

    assertEquals("Mondeo", response.getModel());
  }

  @Test
  void deleteCar() {
    Car newCar = new Car("Ford", "Mondeo", 1750, 20);
    CarRequest request = new CarRequest(newCar);

    carService.addCar(request);
    assertEquals(3, carService.getCars(false).size());

    carService.deleteCar(3);
    assertEquals(2, carService.getCars(false).size());
  }

  @Test
  void findCarById() {
    CarResponse carResponse = carService.findCarById(1);
    assertEquals("Focus", carResponse.getModel());
  }

  @Test
  void updatePricePrDay() {
    carService.updatePricePrDay(1, 2000);
    CarResponse carResponse = carService.findCarById(1);
    assertEquals(2000, carResponse.getPricePrDay());
  }
}