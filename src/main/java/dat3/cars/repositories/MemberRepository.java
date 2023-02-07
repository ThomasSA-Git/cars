package dat3.cars.repositories;

import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
  Boolean existsByEmail(String email);

  Member findMemberByUserName(String userName);
}
