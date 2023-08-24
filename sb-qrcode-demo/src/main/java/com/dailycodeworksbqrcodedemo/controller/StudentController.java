package com.dailycodeworksbqrcodedemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodeworksbqrcodedemo.model.Student;
import com.dailycodeworksbqrcodedemo.service.StudentService;
import com.dailycodeworksbqrcodedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public ResponseEntity<List<Student>> getStudents() {
    return ResponseEntity.ok(studentService.getStudents());
  }

  @GetMapping("/getStudentsQRCode")
  // generate QRCode
  public ResponseEntity<List<Student>> getStudentsQRCode() throws WriterException, IOException {

    List<Student> students = studentService.getStudents();

    if (students.size() != 0) {
      for (Student student : students) {
        QRCodeGenerator.generateQRCode(student);
      }
    }
    return ResponseEntity.ok(studentService.getStudents());
  }

  @PostMapping
  public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    return ResponseEntity.ok(studentService.addStudent(student));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PutMapping
  public ResponseEntity<List<Student>> updateStudents(@RequestBody List<Student> student) {
    return ResponseEntity.ok(studentService.updateStudents(student));
  }

  @DeleteMapping
  public ResponseEntity<Boolean> deleteStudents() {
   return ResponseEntity.ok(studentService.deleteStudents());
  }

}
