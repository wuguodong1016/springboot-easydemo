package com.example.springbootdemo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Student extends JpaRepository<My,Integer>,JpaSpecificationExecutor<My> {
    List<My> findAll();

    @Query(nativeQuery = true,value = "SELECT * FROM my where name=?1 and age=?2")
    List<My> getWu(String name,Integer age);

    List<My> findByAge(Integer age);
}
