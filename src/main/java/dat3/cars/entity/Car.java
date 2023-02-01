package dat3.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String brand;

  String model;

  double pricePrDay;

  double bestDiscount;

  public Car() {
  }

  public Car(int id, String brand, String model, double pricePrDay, double bestDiscount) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.pricePrDay = pricePrDay;
    this.bestDiscount = bestDiscount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public double getPricePrDay() {
    return pricePrDay;
  }

  public void setPricePrDay(double pricePrDay) {
    this.pricePrDay = pricePrDay;
  }

  public double getBestDiscount() {
    return bestDiscount;
  }

  public void setBestDiscount(double bestDiscount) {
    this.bestDiscount = bestDiscount;
  }
}
