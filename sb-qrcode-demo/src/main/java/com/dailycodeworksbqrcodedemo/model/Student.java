package com.dailycodeworksbqrcodedemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Integer mobile;

  public Student(String firstName, String lastName, String email, Integer mobile) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.mobile = mobile;
  }

}
