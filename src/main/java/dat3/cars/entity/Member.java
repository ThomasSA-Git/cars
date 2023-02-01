package dat3.cars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

  @Id
  @Column(nullable = false)
  private String userName;

  private String email;

  @Column(nullable = false)
  private String password;

  private String firstName;

  private String lastName;

  private String street;

  private String city;

  private String zip;

  private boolean approved;

  private int ranking;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime lastEdited;


  public Member(String userName, String email, String password, String firstName, String lastName, String street, String city, String zip) {
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
  }
}
