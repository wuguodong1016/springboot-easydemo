package com.example.springbootdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Student extends JpaRepository<My,Integer>,JpaSpecificationExecutor<My> {
   
	//已经写过的内容
	List<My> findAll();

    @Query(nativeQuery = true,value = "SELECT * FROM my where name=?1 and age=?2")
    List<My> getWu(String name,Integer age);
    
    //对源文件进行修改
    List<My> findByAge(Integer age);
}
