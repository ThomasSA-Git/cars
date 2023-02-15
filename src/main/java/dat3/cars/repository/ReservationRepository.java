package dat3.cars.repository;

import dat3.cars.entity.Car;
import dat3.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  boolean existsByRentalDateAndCar(LocalDate rentalDate, int carId);
/*

  List<Reservation> findByUsername(String member_username);
*/

  Optional<Reservation> findById(int i);
}
