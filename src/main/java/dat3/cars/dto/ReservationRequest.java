package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {

  private String username;

  private int carId;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate rentalDate;


/*
  public ReservationRequest(Reservation r) {
    this.username = r.getMember().getUsername();
    this.carId = r.getCar().getId();
    this.rentalDate = r.getRentalDate();
  }*/

}
