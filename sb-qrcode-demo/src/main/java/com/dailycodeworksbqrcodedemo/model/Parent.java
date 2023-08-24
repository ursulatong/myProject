package com.dailycodeworksbqrcodedemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

@Table(name = "parent")

public class Parent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 交由DataBase自動生成
  private Long id;

  @Column(name = "first_name", nullable = false, updatable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false, updatable = false)
  private String lastName;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Student> students = new ArrayList<>();
  
  public void addStudent(Student student) {
    student.setParent(this);
    students.add(student);
  }


}
