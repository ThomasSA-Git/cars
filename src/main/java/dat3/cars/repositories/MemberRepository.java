package dat3.cars.repositories;

import dat3.cars.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  @Transactional
  @Modifying
  @Query("update Member m set m.ranking = ?1 where m.userName = ?2")
  int updateRankingByUserName(@NonNull int ranking, @NonNull String userName);

  Boolean existsByEmail(String email);

  ResponseEntity<Boolean> update(String userName, Member member);

  Member findMemberByUserName(String userName);

  @Override
  Optional<Member> findById(String s);
}
