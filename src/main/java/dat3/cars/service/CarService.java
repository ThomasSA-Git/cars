package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repositories.CarRepository;
import org.springframework.http.HttpStatus;
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

  public void deleteCar(int id){
    carRepository.delete(carRepository.getReferenceById(id));
  }

  public CarResponse findCarById(int id){
    Car foundCar = carRepository.findCarById(id);
    return new CarResponse(foundCar, false);
  }

  public void updatePricePrDay(int id, double price){
    carRepository.updatePricePrDayById(price, id);
  }
}
