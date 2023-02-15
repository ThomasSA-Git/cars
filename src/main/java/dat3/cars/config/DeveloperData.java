package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
@EnableJpaRepositories(basePackages = {"dat3.security.repository", "dat3.cars.repository"})
public class DeveloperData implements ApplicationRunner {

  private CarRepository carRepository;

  private MemberRepository memberRepository;

  private ReservationRepository reservationRepository;

  @Autowired
  UserWithRolesRepository userWithRolesRepository;
  final String passwordUsedByAll = "test12";


 public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
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
/*
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
    member3.setPhones(phones);*/

    List<Member> memberEntities = new ArrayList<Member>();
    memberEntities.add(member1);
    memberEntities.add(member2);
    memberEntities.add(member3);

    memberRepository.saveAll(memberEntities);

    Reservation reservation1 = new Reservation(member1, car1);
    Reservation reservation2 = new Reservation(member2, car2);
    List<Reservation> reservationList = new ArrayList<>();
    reservationList.add(reservation1);
    reservationList.add(reservation2);


    setupUserWithRoleUsers();

    reservationRepository.saveAll(reservationList);

  }



  /*****************************************************************************************
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
   *****************************************************************************************/
  private void setupUserWithRoleUsers() {

    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");
    UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }

}
