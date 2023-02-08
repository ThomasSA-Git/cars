package dat3.cars.repositories;

import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  boolean dataIsArranged = false;

  @BeforeEach
  void setup() {
    if (!dataIsArranged) {
      List<Member> members = List.of(
          new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800"),
          new Member("m2", "mm@a.dk", "pw", "bb", "bbb", "bbbb", "bbbb", "1234")
      );
      memberRepository.saveAll(members);
      dataIsArranged = true;
    }
  }

  @Test
  void testGetAll() {
    List<Member> members = memberRepository.findAll();
    assertEquals(2, members.size());
  }

  @Test
  void existsByEmail(){
    assertTrue(memberRepository.existsByEmail("m1@a.dk"));
  }

}