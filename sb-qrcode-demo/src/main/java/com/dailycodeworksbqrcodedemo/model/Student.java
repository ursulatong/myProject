package com.dailycodeworksbqrcodedemo.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "student")

public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 交由DataBase自動生成
  private Long id;

  @Column(name = "student_no", nullable = false, updatable = false)
  private String studentNo;

  @Column(name = "first_name", nullable = false, updatable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false, updatable = false)
  private String lastName;

  @Column(name = "user_name", nullable = true, updatable = false)
  private String userName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "mobile", nullable = false)
  private int mobile;

  @Column(name = "enrollment_date", nullable = false)
  private LocalDate enrollmentDate;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;
  
  @Column(name = "nationality", nullable = false)
  private String nationality;

  @Column(name = "address", nullable = true)
  private String address;

  @ManyToOne (cascade = CascadeType.PERSIST)
  @JoinColumn(name = "parent_id")
  private Parent parent;


}
