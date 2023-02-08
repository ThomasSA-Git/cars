package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DeveloperData implements ApplicationRunner {

  private CarRepository carRepository;

  private MemberRepository memberRepository;

  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    //Create cars
    Car car1 = new Car("Ford", "Focus", 1250, 20);
    Car car2 = new Car("Ford", "Fiesta", 1500, 20);
    Car car3 = new Car("Ford", "Mondeo", 1750, 20);


    List<Car> carEntities = new ArrayList<>();
    carEntities.add(car1);
    carEntities.add(car2);
    carEntities.add(car3);

    carRepository.saveAll(carEntities);

    //Create members
    Member member1 = new Member("test1", "jk@mail.com", "Kode", "John", "Doe", "Testgade 1", "Testby", "2500");
    Member member2 = new Member("test2", "jik@mail.com", "Kode", "Jimmy", "Doe", "Testgade 2", "Testby", "2600");
    Member member3 = new Member("test3", "tk@mail.com", "Kode", "Timmy", "Doe", "Testgade 3", "Testby", "2700");

    //Add favourite color
    List<String> color = new ArrayList<>();
    color.add("green");
    color.add("red");

    member1.setFavoriteCarColors(color);
    member2.setFavoriteCarColors(color);
    member3.setFavoriteCarColors(color);

    //Add phone number
    Map<String, String> phones = new HashMap<>();

    phones.put("Work", "36363636");
    phones.put("Mobile", "58585858");
    phones.put("Home", "75504659");
    member1.setPhones(phones);
    member2.setPhones(phones);
    member3.setPhones(phones);

    List<Member> memberEntities = new ArrayList<Member>();
    memberEntities.add(member1);
    memberEntities.add(member2);
    memberEntities.add(member3);

    memberRepository.saveAll(memberEntities);

  }
}
