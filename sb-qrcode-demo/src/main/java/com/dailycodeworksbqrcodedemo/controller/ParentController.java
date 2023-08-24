package com.dailycodeworksbqrcodedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodeworksbqrcodedemo.model.Parent;
import com.dailycodeworksbqrcodedemo.service.ParentService;

@RestController
@RequestMapping("/parents")
public class ParentController {

  @Autowired
  private ParentService parentService;

  @GetMapping
  public ResponseEntity<List<Parent>> getParents() {
    return ResponseEntity.ok(parentService.getParents());
  }

  @PostMapping
  public ResponseEntity<Parent> addParent(@RequestBody Parent parent) {
    return ResponseEntity.ok(parentService.addParent(parent));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Parent> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(parentService.findById(id));
  }

  @PutMapping
  public ResponseEntity<List<Parent>> updateParent(@RequestBody List<Parent> parent) {
    return ResponseEntity.ok(parentService.updateParent(parent));
  }

}
