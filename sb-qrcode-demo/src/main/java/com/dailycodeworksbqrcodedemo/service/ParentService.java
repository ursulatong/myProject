package com.dailycodeworksbqrcodedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodeworksbqrcodedemo.model.Parent;
import com.dailycodeworksbqrcodedemo.repository.ParentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentService {

  @Autowired
  private ParentRepository parentRepository;

  public List<Parent> getParents() {
    return parentRepository.findAll();

  }

  public Parent addParent(Parent parent) {
    return parentRepository.save(parent);
  }

  public Parent findById(Long id) {
    return parentRepository.findById(id).orElseThrow(() -> new RuntimeException("Parent not found"));
  }

  public List<Parent> updateParent(List<Parent> parent) {
    return parentRepository.saveAllAndFlush(parent);
  }

  public void deleteParents() {
    parentRepository.deleteAll();
  }





  
}
