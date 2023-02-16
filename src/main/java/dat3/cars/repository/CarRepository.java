package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

  //uge 3, opg 8.A
  List<Car> findCarsByBrandAndModel(String brand, String model);
  Boolean existsByModel(String model);

  Car findCarById(int id);
}
