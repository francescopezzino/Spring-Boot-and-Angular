package com.example.springboot.superheroes.user.data;

import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private UUID id;

  private String email;
  private String mobileNumber;
  private String password;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
