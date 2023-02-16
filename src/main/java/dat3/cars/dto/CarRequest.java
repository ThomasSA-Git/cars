package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {

  private String brand;

  private String model;

  private double pricePrDay;

  private int bestDiscount;


  public static Car getCarEntity(CarRequest c){
    return Car.builder().brand(c.brand).model(c.model).pricePrDay(c.pricePrDay).bestDiscount(c.bestDiscount).build();
  }

  public CarRequest(Car c) {
    this.brand = c.getBrand();
    this.model = c.getModel();
    this.pricePrDay = c.getPricePrDay();
    this.bestDiscount = c.getBestDiscount();
  }
}
