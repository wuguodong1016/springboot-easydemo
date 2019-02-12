package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mybatis")
public class MybatisController {

    @Autowired
    private MyDao mydao;

    @GetMapping(value = "/list")
    public List<My> findAll(){
        return mydao.findAll();
    }

    @GetMapping(value = "/my/{name}")
    public List<My> findByName(@PathVariable String name){
        return mydao.findByName(name);
    }
}
