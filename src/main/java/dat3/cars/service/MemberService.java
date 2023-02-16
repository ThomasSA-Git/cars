package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

  private MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public List<MemberResponse> getMembers(boolean includeAll) {
    List<Member> members = memberRepository.findAll();
/*    List<MemberResponse> memberResponses = new ArrayList<>();
    for (Member m: members) {
      MemberResponse mr = new MemberResponse(m, includeAll);
      memberResponses.add(mr);
    }*/
    List<MemberResponse> memberResponses = members.stream().map(m -> new MemberResponse(m, includeAll)).toList();

    return memberResponses;
  }

  public MemberResponse addMember(MemberRequest memberRequest) {
    //Later you should add error checks --> Missing arguments, email taken etc.
    if (memberRepository.existsById(memberRequest.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID already exist");
    }
    if (memberRepository.existsByEmail(memberRequest.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this Email already exist");
    }

    Member newMember = MemberRequest.getMemberEntity(memberRequest);
    newMember = memberRepository.save(newMember);

    return new MemberResponse(newMember, false);
  }

  public ResponseEntity<Boolean> deleteMember(String username) {
    try {
      if (memberRepository.existsById(username)) {
        memberRepository.deleteById(username);
        return new ResponseEntity<>(true, HttpStatus.OK);
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete member, he is probably 'Active'");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete member, he's probably 'Active'");
    }
  }

  public MemberResponse findMemberByUserId(String userName) {
    Member foundMember = memberRepository.findById(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));
    //Member foundMember = memberRepository.findMemberByUserName(userName);
    return new MemberResponse(foundMember, true);
  }

  public void updateRanking(String userName, int ranking) {
    Member updatedMember = memberRepository.findById(userName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));

    updatedMember.setRanking(ranking);
    memberRepository.save(updatedMember);
  }

  public ResponseEntity<Boolean> updateMember(MemberRequest body, String username) {
    Member memberToEdit = memberRepository.findById(username).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID does not exist"));

    //Member can not change his username (primary key), ranking, approved and timestamps
    memberToEdit.setEmail(body.getEmail());
    memberToEdit.setPassword(body.getPassword());
    memberToEdit.setFirstName(body.getFirstName());
    memberToEdit.setLastName(body.getLastName());
    memberToEdit.setStreet(body.getStreet());
    memberToEdit.setZip(body.getZip());
    memberToEdit.setCity(body.getCity());
    memberRepository.save(memberToEdit);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  public ResponseEntity<Boolean> editMemberV2(MemberRequest body, String username) {
    Member memberToEdit = memberRepository.findById(username).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this ID does not exist"));

    Optional.ofNullable(body.getEmail()).ifPresent(memberToEdit::setEmail);
    Optional.ofNullable(body.getPassword()).ifPresent(memberToEdit::setPassword);
    Optional.ofNullable(body.getFirstName()).ifPresent(memberToEdit::setFirstName);
    Optional.ofNullable(body.getLastName()).ifPresent(memberToEdit::setLastName);
    Optional.ofNullable(body.getStreet()).ifPresent(memberToEdit::setStreet);
    Optional.ofNullable(body.getZip()).ifPresent(memberToEdit::setZip);
    Optional.ofNullable(body.getCity()).ifPresent(memberToEdit::setCity);
    memberRepository.save(memberToEdit);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  //uge 3 opg 8
  public int numberOfReservationsByMember(String username) {
    Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this ID does not exist"));
    int numberOfReservations = member.getReservations().size();
    return numberOfReservations;
  }

}
