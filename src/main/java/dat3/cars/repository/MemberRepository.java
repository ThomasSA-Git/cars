package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {


  Boolean existsByEmail(String email);


  Member findMemberByUsername(String username);

  @Override
  Optional<Member> findById(String s);
}
