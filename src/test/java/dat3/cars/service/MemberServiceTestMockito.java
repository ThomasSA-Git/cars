
package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MemberServiceTestMockito {

  @Mock
  MemberRepository memberRepository;

  MemberService memberService;

  @BeforeEach
  void setUp() {
    memberService = new MemberService(memberRepository);
  }

  @Test
  void getMembersAdmin() {
    Member m1 = new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800");
    Member m2 = new Member("m2", "m2@a.dk", "test12", "aa", "hansen", "xx vej 34", "Lyngby", "2800");
    m1.setCreated(LocalDateTime.now());
    m2.setCreated(LocalDateTime.now());
    Mockito.when(memberRepository.findAll()).thenReturn(List.of(m1, m2));

    List<MemberResponse> members = memberService.getMembers(true);

    assertEquals(2, members.size());
    assertNotNull(members.get(0).getCreated());
  }

  @Test
  void getMembersNotAdmin() {
    Member m1 = new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800");
    Member m2 = new Member("m2", "m2@a.dk", "test12", "aa", "hansen", "xx vej 34", "Lyngby", "2800");
    m1.setCreated(LocalDateTime.now());
    m2.setCreated(LocalDateTime.now());
    Mockito.when(memberRepository.findAll()).thenReturn(List.of(m1, m2));

    List<MemberResponse> members = memberService.getMembers(false);

    assertEquals(2, members.size());
    assertNull(members.get(0).getCreated());
  }

  @Test
  void addMember() {
    Member newMember = new Member("aa", "a@a.dk", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa");
    Mockito.when(memberRepository.save(any(Member.class))).thenReturn(newMember);

    MemberRequest memberRequest = new MemberRequest(newMember);
    MemberResponse addedMember = memberService.addMember(memberRequest);

    assertEquals("a@a.dk", addedMember.getEmail());
  }

  @Test
  void findMemberById() {
    Member m1 = new Member("m1", "m1@a.dk", "test12", "bb", "Olsen", "xx vej 34", "Lyngby", "2800");

    m1.setCreated(LocalDateTime.now());


    Mockito.when(memberRepository.findById("m1")).thenReturn(Optional.of(m1));

    MemberResponse memberResponse = memberService.findMemberByUserId("m1");

    assertEquals("m1@a.dk", memberResponse.getEmail());
  }

/*
  @Test
  void deleteMember() {
    Member newMember = new Member("aa", "a@a.dk", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa");
    Mockito.when(memberRepository.save(any(Member.class))).thenReturn(newMember);

    MemberRequest memberRequest = new MemberRequest(newMember);
    MemberResponse memberDelete = memberService.addMember(memberRequest);
    memberService.deleteMember(memberDelete.getUserName());

    assertNotEquals(memberRepository.findAll(), containsInAnyOrder(hasProperty("userName", is("aa"))));
  }




  @Test
  void updateRanking() {
    Member newMember = new Member("aa", "aaa", "a@a.dk", "aaa", "aaa", "aaa", "aaa", "aaa");
    Mockito.when(memberRepository.updateRankingByUserName(10, "aa").thenReturn());
    MemberRequest memberRequest = new MemberRequest(newMember);

    MemberResponse found = memberService.addMember(memberRequest);
   memberService.updateRanking("aa", 10);
    assertEquals(10, found.getRanking());
  }
*/

}
