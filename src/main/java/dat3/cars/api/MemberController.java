package dat3.cars.api;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
class MemberController {

  private MemberService memberService;


  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  //ADMIN ONLY
  @GetMapping
  List<MemberResponse> getMembers(){ return memberService.getMembers(false);}

  //ADMIN
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username) throws Exception {return memberService.findMemberByUserId(username);}

  //ANONYMOUS
  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping()
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }

  //MEMBER
  @PutMapping("/{username}")
  ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
    return memberService.updateMember(body, username);
  }

  //Security ADMIN  (eventually we will change it to use the currently logged in user)
  @PutMapping("/v2/{username}")
  ResponseEntity<Boolean> editMemberV2(@RequestBody MemberRequest body, @PathVariable String username){
    return memberService.editMemberV2(body, username);
  }

  //ADMIN
  @PatchMapping("/ranking/{username}/{value}")
  void setRankingForUser(@PathVariable String username, @PathVariable int value) {
    memberService.updateRanking(username, value);
  }

  //ADMIN
  @DeleteMapping("/{username}")
  ResponseEntity<Boolean> deleteMemberByUsername(@PathVariable String username) {
    return memberService.deleteMember(username);
  }


}
