package dat3.cars.dto;

import dat3.cars.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRequest {
  String userName;
  String email;
  String password;
  String firstName;
  String lastName;
  String street;
  String city;
  String zip;

  public static Member getMemberEntity(MemberRequest m){
    return new Member(m.userName,m.getEmail(),m.getPassword(), m.firstName, m.lastName,m.getStreet(), m.getCity(), m.getZip());
  }

  // Member to MemberRequest conversion
  public MemberRequest(Member m){
    this.userName = m.getUserName();
    this.email = m.getEmail();
    this.password = m.getPassword();
    this.street = m.getStreet();
    this.city = m.getCity();
    this.zip = m.getZip();
  }
}
