package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class MemberServiceTestH2 {


  @Autowired
  private MemberRepository memberRepository;


  MemberService memberService;

  private boolean dataIsInitialized = false;


  @BeforeEach
  public void setupData() {
    memberService = new MemberService(memberRepository);
    if (!dataIsInitialized) {
      List<Member> members = List.of(
          new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800"),
          new Member("m2", "mm@a.dk", "pw", "bb", "bbb", "bbbb", "bbbb", "1234")
      );
      memberRepository.saveAll(members);
      dataIsInitialized = true;
    }
  }


  @Test
  void getMembers() {
    List<MemberResponse> response = memberService.getMembers(false);
    assertEquals(2, response.size());
    assertThat(response, containsInAnyOrder(hasProperty("email", is("m1@a.dk")), hasProperty("email", is("mm@a.dk"))));

  }

  @Test
  void addMember() {
    Member newMember = new Member("m3", "m3@a.dk", "pw", "aa", "aaa", "aaaa", "aaaa", "1234");
    MemberRequest request = new MemberRequest(newMember);
    MemberResponse response = memberService.addMember(request);

    assertEquals("m3@a.dk", response.getEmail());
  }

  @Test
  void addMemberException() {
    Member newMember = new Member("m1", "m3@a.dk", "pw", "aa", "aaa", "aaaa", "aaaa", "1234");
    MemberRequest request = new MemberRequest(newMember);

    Throwable exception = assertThrows(ResponseStatusException.class, () ->
        memberService.addMember(request));
    String expectedMessage = "Member with this ID already exist";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void deleteMember() {
    Member newMember = new Member("m3", "m3@a.dk", "pw", "aa", "aaa", "aaaa", "aaaa", "1234");
    MemberRequest request = new MemberRequest(newMember);

    memberService.addMember(request);
    assertEquals(3, memberService.getMembers(false).size());

    memberService.deleteMember(request.getUserName());
    assertEquals(2, memberService.getMembers(false).size());
  }

  @Test
  void findMemberByUserId() {
    MemberResponse memberResponse = memberService.findMemberByUserId("m1");
    assertEquals("m1@a.dk", memberResponse.getEmail());
  }

  @Test
  void findMemberByUserIdException() {
    Throwable exception = assertThrows(ResponseStatusException.class, () ->
        memberService.findMemberByUserId("m5"));
    String expectedMessage = "Member with this ID does not exist";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void updateRanking() {

    memberService.updateRanking("m1", 10);
    MemberResponse memberResponse = memberService.findMemberByUserId("m1");
    assertEquals(10, memberResponse.getRanking());
  }

/* Virker ikke endnu
  @Test
  void updateMember() {
    Member test = new Member("m4", "122@.dk", "12", "123", "133", "123", "123", "123");
    MemberRequest newMem = new MemberRequest(test);
    memberService.addMember(newMem);
    Member updateMember = new Member("m4", "opdateret@.dk", "pwop", "firstName", "lastName", "Street", "City", "1234op");

    MemberRequest update = new MemberRequest(updateMember);

    memberService.updateMember(update, "m4");
    MemberResponse memberResponse = memberService.findMemberByUserId("m4");
    assertEquals("m4", memberResponse.getUserName());
    assertEquals("opdateret@.dk", memberResponse.getEmail());
    assertEquals("firstName", memberResponse.getFirstName());
    assertEquals("lastName", memberResponse.getLastName());
    assertEquals("Street", memberResponse.getStreet());
    assertEquals("City", memberResponse.getCity());
    assertEquals("1234op", memberResponse.getZip());
  }*/
}

