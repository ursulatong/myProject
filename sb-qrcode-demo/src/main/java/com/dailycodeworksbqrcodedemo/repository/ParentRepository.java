package com.dailycodeworksbqrcodedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodeworksbqrcodedemo.model.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long>{


  
}
