package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

  private CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarResponse> getCars(boolean includeAll){
    List<Car> cars = carRepository.findAll();

    List<CarResponse> carResponses = cars.stream().map(c->new CarResponse(c, includeAll)).toList();

    return carResponses;
  }

  public CarResponse addCar(CarRequest carRequest){

    if(carRepository.existsByModel(carRequest.getModel())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Model with this name already exist");
    }

    Car newCar =  CarRequest.getCarEntity(carRequest);
    carRepository.save(newCar);

    return new CarResponse(newCar, false);
  }


  public ResponseEntity<Boolean> deleteCar(int id) {
    Car carToDelete = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
    try {
      carRepository.delete(carToDelete);
      return ResponseEntity.ok(true);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete car. Most likely because it part of a rental/reservation");
    }
  }

  public CarResponse findCarById(int id){
    Car foundCar = carRepository.findCarById(id);
    return new CarResponse(foundCar, false);
  }

  public void updatePricePrDay(int id, double price){
    Car updatedCar = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID does not exist"));
    updatedCar.setPricePrDay(price);
    carRepository.save(updatedCar);
  }

  public ResponseEntity<Boolean> updateCar(CarRequest body, int id) {
    Car carToEdit = carRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID does not exist"));
    //ID can not be changed
    carToEdit.setBrand(body.getBrand());
    carToEdit.setModel(body.getModel());
    carToEdit.setPricePrDay(body.getPricePrDay());
    carToEdit.setBestDiscount(body.getBestDiscount());
    carRepository.save(carToEdit);
    return ResponseEntity.ok(true);
  }

  //uge 3, opg 8.A
  public List<CarResponse> findCarsByBrandAndModel(String brand, String model){

    List<Car> carList = carRepository.findCarsByBrandAndModel(brand, model);

    List<CarResponse> carResponseList = carList.stream().map(c ->new CarResponse(c, true)).toList();

    return  carResponseList;
  }

  public double avgPricePrDay(){
    List<Car> cars = carRepository.findAll();
    double totalPricePrDay = 0;
    for (Car c: cars) {
      totalPricePrDay += c.getPricePrDay();
    }
    double avgPricePrday = totalPricePrDay / cars.size();
    return avgPricePrday;
  }
}
