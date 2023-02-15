package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @ManyToOne
  Member member;

  @ManyToOne
  Car car;

  @CreationTimestamp
  private LocalDateTime reservationDate;

  private LocalDate rentalDate;

  public Reservation(Member member, Car car, LocalDate rentalDate) {
    this.member = member;
    this.car = car;
    this.rentalDate = rentalDate;
  }

}
