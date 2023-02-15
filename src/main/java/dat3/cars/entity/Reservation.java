package dat3.cars.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Reservation {

  @Id
  Integer id;

  @CreationTimestamp
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime reservationDate;

/*  @UpdateTimestamp
  private LocalDateTime lastEdited;*/
}
