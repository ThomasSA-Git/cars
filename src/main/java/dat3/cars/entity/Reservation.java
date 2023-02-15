package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {

  @Id
  Integer id;

  @ManyToOne
  Member member;

  @ManyToOne
  Car car;

  @CreationTimestamp
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime reservationDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime rentalDate;

  public Reservation(Member member, Car car) {
    this.member = member;
    this.car = car;
  }

}
