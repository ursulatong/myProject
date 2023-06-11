package com.dailycodeworksbqrcodedemo.model;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findAll();

  }

  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  // it's optional, to not get pointer exception
  public Student findById(Long id) {
    return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
  }

  public Student updateStudent(Student student){
    return studentRepository.saveAndFlush(student);
  }

}
