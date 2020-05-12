package com.example.demo.web;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.entity.User;

/**
 * Controller公共组件
 * 
 * @projectName:demo
 * @author:caog
 * @date:2020年5月8日 下午3:54:07
 * @version 1.0
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
