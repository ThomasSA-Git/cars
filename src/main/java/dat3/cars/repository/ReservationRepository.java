package dat3.cars.repository;

import dat3.cars.entity.Car;
import dat3.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


  boolean existsByCar(Car c);

  Optional<Reservation> findById(int i);
}
