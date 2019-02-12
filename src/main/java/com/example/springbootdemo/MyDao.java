package com.example.springbootdemo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface MyDao {

    @Select("select * from my")
    List<My> findAll();

    @Select("select * from my where name = #{name}")
    List<My> findByName(@Param("name") String name);
 }
