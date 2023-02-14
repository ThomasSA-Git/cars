package dat3.cars.entity;

import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Reservation {

  @CreationTimestamp
  private LocalDateTime reservationDate;

/*  @UpdateTimestamp
  private LocalDateTime lastEdited;*/
}
