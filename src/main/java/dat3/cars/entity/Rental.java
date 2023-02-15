/*
package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  Member member;

  @ManyToOne
  Car car;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime rentalDate;

  public Rental(Member member, Car car, LocalDateTime rentalDate) {
    this.member = member;
    this.car = car;
    this.rentalDate = rentalDate;
  }

}
*/
