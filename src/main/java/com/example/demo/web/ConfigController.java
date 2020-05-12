package com.example.demo.web;

import com.example.demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: peixun
 * @author: caog
 * @date: 2020年05月07日 16:32
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/index")
public class ConfigController {

    @Value("${gk.name}")
    private String name;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Environment env;

    @GetMapping
    public String index() {
        //return "name:" + appConfig.getName() + ",age:" + appConfig.getAge();
        return env.getProperty("gk.name");

    }
}
