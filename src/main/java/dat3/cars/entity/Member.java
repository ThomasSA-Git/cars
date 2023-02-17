package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
/*

  @Id
  @Column(nullable = false)
  private String userName;

  private String email;

  @Column(nullable = false)
  private String password;
*/

  private String firstName;

  private String lastName;

/*  @ElementCollection
  private List<String> favoriteCarColors = new ArrayList<>();*/

/*
  @ElementCollection
  @MapKeyColumn(name = "Description")
  @Column(name = "phoneNumber")
  private Map<String,String> phones = new HashMap<>();
*/


  private String street;

  private String city;

  private String zip;

  private boolean approved;

  private int ranking;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime lastEdited;


  public Member(String userName, String email, String password, String firstName, String lastName, String street, String city, String zip) {
    super(userName, password, email);
   /* this.userName = userName;
    this.email = email;
    this.password = password;*/
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;

  }

}
