package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: peixun
 * @author: caog
 * @date: 2020年05月07日 16:37
 * @version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "gk" )
public class AppConfig {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
