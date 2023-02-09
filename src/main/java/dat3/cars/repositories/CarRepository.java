package dat3.cars.repositories;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface CarRepository extends JpaRepository<Car, Integer> {
  @Transactional
  @Modifying
  @Query("update Car c set c.pricePrDay = ?1 where c.id = ?2")
  int updatePricePrDayById(@NonNull double pricePrDay, @NonNull int id);

  ResponseEntity<Boolean> update(int id, Car car);
  Boolean existsByModel(String model);

  Car findCarById(int id);
}
