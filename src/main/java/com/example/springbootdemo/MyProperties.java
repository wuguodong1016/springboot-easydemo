package com.example.springbootdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class MyProperties {

	//huncun1
    private String name;
    private String age;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    //tijiao2
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
