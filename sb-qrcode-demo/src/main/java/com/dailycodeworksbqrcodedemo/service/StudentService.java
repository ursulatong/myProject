package com.dailycodeworksbqrcodedemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dailycodeworksbqrcodedemo.model.Parent;
import com.dailycodeworksbqrcodedemo.model.Student;
import com.dailycodeworksbqrcodedemo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findAll();

  }

  public Student addStudent(Student student) {
    // Parent parent = new Parent();
    // parent.setFirstName(student.getParent().getFirstName());
    // parent.setLastName(student.getParent().getLastName());
    return studentRepository.save(student);
  }

  // it's optional, to not get pointer exception
  public Student findById(Long id) {
    return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
  }

  public List<Student> updateStudents(List<Student> student) {
    return studentRepository.saveAllAndFlush(student);
  }

  public Boolean deleteStudents() {
    studentRepository.deleteAll();
    ;
    return true;
  }
}
