package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

  private int id;
  private String username;
  private String brand;
  private int carId;

  @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
  private LocalDate rentalDate;

  public ReservationResponse(Reservation r) {
    this.username = r.getMember().getUsername();
    this.brand = r.getCar().getBrand();
    this.carId = r.getCar().getId();
    this.rentalDate = r.getRentalDate();
  }

}
