package com.dailycodeworksbqrcodedemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodeworksbqrcodedemo.model.Student;
import com.dailycodeworksbqrcodedemo.model.StudentService;
import com.dailycodeworksbqrcodedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  //generate QRCode
  public ResponseEntity<List<Student>> getStudents() throws WriterException, IOException {

    List<Student> students = studentService.getStudents();

    if (students.size() != 0){
      for (Student student: students){
        QRCodeGenerator.generateQRCode(student);
      }
    }
    return ResponseEntity.ok(studentService.getStudents());
  }

  @PostMapping
  public Student addStudent (@RequestBody Student student){
    return studentService.addStudent(student);  
  }

  @GetMapping ("/{id}")
  public Student findById (@PathVariable("id") Long id){
    return studentService.findById(id);
  }







}
