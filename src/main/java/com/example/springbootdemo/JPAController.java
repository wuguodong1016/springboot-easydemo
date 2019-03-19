package com.example.springbootdemo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/jpa")
public class JPAController {

    @Autowired
    private MyProperties properties;
    
    @Autowired
    private Student sturdent;

    //这个不是从数据库获取的，是从配置文件中读取的
    @GetMapping(value = "/hello")
    public String say(){
        return properties.getName();
    }

    @GetMapping(value = "/my/age/{age}")
    public List<My> mylistByAge(@PathVariable Integer age){
        return sturdent.findByAge(age);
    }

    @GetMapping(value = "/my/all")
    public List<My> findAll(){
        return sturdent.findAll();
    }

    @GetMapping(value = "/my/{name}/{age}")
    public List<My> getWu(@PathVariable String name,@PathVariable Integer age){
       return sturdent.getWu(name,age);
    }

    //进行分页查询
    @GetMapping(value = "/page/{page}/{rows}")
    public Map<String,Object> getAll(@PathVariable Integer page,@PathVariable Integer rows){

        Pageable pageable = new PageRequest(page - 1, rows);
        Page<My> pageData = sturdent.findAll(pageable);
        Map<String,Object> result = new HashMap<>();
        result.put("total",pageData.getNumberOfElements());
        result.put("rows",pageData.getContent());
        return result;
    }

    //带条件的分页查询
    @GetMapping(value = "/page2/{page}/{rows}/{name}")
    public Map<String,Object> getbeforAge(@PathVariable Integer page,@PathVariable Integer rows,@PathVariable String name){

        Specification<My> specification =new Specification<My>(){

            @Override
            public Predicate toPredicate(Root<My> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<Predicate>();

                if(StringUtils.isNotBlank(name)){
                    Predicate p3 =cb.equal(root.get("name").as(String.class),name);
                    list.add(p3);
                }

                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<My> pageData = sturdent.findAll(specification,pageable);
        Map<String,Object> result = new HashMap<>();
        result.put("total",pageData.getNumberOfElements());
        result.put("rows",pageData.getContent());
        return result;
    }
}
