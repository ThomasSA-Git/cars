package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

  @Autowired
  private CarRepository carRepository;

  boolean dataIsArranged = false;

  @BeforeEach
  void setup() {
    if (!dataIsArranged) {
      List<Car> cars = List.of(
          Car.builder().brand("Ford").model("Focus").pricePrDay(1250).bestDiscount(20).build(),
          Car.builder().brand("Ford").model("Fiesta").pricePrDay(1500).bestDiscount(20).build()
      );
      carRepository.saveAllAndFlush(cars);
      dataIsArranged = true;
    }
  }

  @Test
  void testGetAll() {
    List<Car> cars = carRepository.findAll();
    assertEquals(2, cars.size());
  }

  @Test
  void existsByModel(){
    assertTrue(carRepository.existsByModel("Focus"));
  }

}
