package com.example.demo.utils.shiro.service;

import java.util.Set;

import com.example.demo.entity.SysUserToken;
import com.example.demo.entity.User;

/**
 * @Description: shiro相关接口
 * @author: kylin
 * @create: 2018-01-31 10:06
 **/
public interface ShiroService {

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * 
     * @param userId
     */
    User queryUser(Long userId);

    /**
     * 获取用户权限
     * 
     * @param userId
     * @return
     * @author:caog
     * @createTime:2020年5月9日 上午10:20:40
     */
    Set<String> getUserPermissions(long userId);

}
