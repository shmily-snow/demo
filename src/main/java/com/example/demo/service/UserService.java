package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

public interface UserService {

    /***
     * 根据用户名查询系统用户
     * 
     * @param username
     * @return com.example.demo.entity.User
     * @author caog
     * @createTime 2020/5/8 21:03
     *
     */
    User queryByUserName(String username);

    /***
     * 查询用户列表
     * 
     * @param map
     * @return java.util.List<com.example.demo.entity.User>
     * @author caog
     * @createTime 2020/5/8 22:03
     *
     */
    List<User> queryList(Map<String, Object> map);

    /***
     * 查询总数
     * 
     * @param map
     * @return int
     * @author caog
     * @createTime 2020/5/8 22:03
     *
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 根据userId查询用户
     * 
     * @param userId
     * @return
     * @author:caog
     * @createTime:2020年5月9日 下午3:01:36
     */
    User queryObject(Long userId);

    /**
     * 保存用户
     * 
     * @param user
     * @author:caog
     * @createTime:2020年5月9日 下午3:23:13
     */
    void save(User user);

    /**
     * 修改用户
     * 
     * @param user
     * @return
     * @author:caog
     * @createTime:2020年5月9日 下午3:46:17
     */
    int update(User user);
}
