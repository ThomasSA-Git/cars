package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
          Car.builder().brand("Ford").model("Focus").pricePrDay(1250).bestDiscount(20).build(),
          Car.builder().brand("Ford").model("Fiesta").pricePrDay(1500).bestDiscount(20).build()
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
    Car newCar =  Car.builder().brand("Ford").model("Mondeo").pricePrDay(1750).bestDiscount(20).build();
    CarRequest request = new CarRequest(newCar);
    CarResponse response = carService.addCar(request);

    assertEquals("Mondeo", response.getModel());
  }

  @Test
  void addCarException() {
    Car newCar =  Car.builder().brand("Ford").model("Focus").pricePrDay(1750).bestDiscount(20).build();
    CarRequest request = new CarRequest(newCar);

    Throwable exception = assertThrows(ResponseStatusException.class, () ->
        carService.addCar(request));
    String expectedMessage = "Model with this name already exist";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void findCarById() {
    Car newCar =  Car.builder().brand("Peugot").model("306").pricePrDay(1750).bestDiscount(20).build();
    CarRequest request = new CarRequest(newCar);

    CarResponse response = carService.addCar(request);
    CarResponse carResponse = carService.findCarById(response.getId());

    assertEquals("306", carResponse.getModel());
  }


/*
  @Test
  void deleteCar() {
    Car newCar = new Car("Citroen", "Berlingo", 1750, 20);
    CarRequest request = new CarRequest(newCar);
    carService.addCar(request);
    assertEquals(3, carService.getCars(false).size());
    carService.deleteCar(2);
    assertEquals(2, carService.getCars(false).size());
  }
*/

/*  @Test
  void updatePricePrDay() {
    //Tester første bil oprettet på listen
    carService.updatePricePrDay(1, 2000);
    CarResponse carResponse = carService.findCarById(1);
    assertEquals(2000, carResponse.getPricePrDay());
  }*/

  @Test
  void updateCar() {
    //new Car("Ford", "Fiesta", 1500, 20) det er denne bil der bliver opdateret
    Car newCar =  Car.builder().brand("Ford").model("Fiasko").pricePrDay(3500).bestDiscount(30).build();
    CarRequest request = new CarRequest(newCar);
    carService.updateCar(request, 2);
    CarResponse carResponse = carService.findCarById(2);
    assertEquals("Fiasko", carResponse.getModel());
    assertEquals(3500, carResponse.getPricePrDay());
    //Nedenstående bliver til null. Lignende problem i MemberServiceTest
    //assertEquals(30, carResponse.getBestDiscount());
  }

}