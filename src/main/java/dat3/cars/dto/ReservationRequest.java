package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {

  Member member;

  Car car;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime reservationDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime rentalDate;

  public static Reservation getReservationEntity(ReservationRequest r){
    return new Reservation(r.getMember(), r.getCar());
  }

  public ReservationRequest(Reservation r) {
    this.member = r.getMember();
    this.car = r.getCar();
  }

}