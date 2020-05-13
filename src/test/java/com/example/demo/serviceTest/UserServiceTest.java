package com.example.demo.serviceTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.baseTest.SpringTestCase;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

public class UserServiceTest extends SpringTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void selectUserByIdTest() {
        User user = userService.queryObject(1L);
        logger.info("查找结果,userName:" + user.getUserName());
    }

}
