package dat3.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

  @Id
  String userName;

  String email;

  Long password;

  String firstName;

  String lastName;

  String street;

  String city;

  int zip;

  //boolean approved;

  //ranking?


  public Member() {
  }

  public Member(String userName, String email, Long password, String firstName, String lastName, String street, String city, int zip) {
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getPassword() {
    return password;
  }

  public void setPassword(Long password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getZip() {
    return zip;
  }

  public void setZip(int zip) {
    this.zip = zip;
  }
}
