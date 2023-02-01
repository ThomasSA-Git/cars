package dat3.cars.config;

import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DeveloperData implements ApplicationRunner {

  @Autowired
  CarRepository carRepository;

  @Autowired
  MemberRepository memberRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {

  }
}
