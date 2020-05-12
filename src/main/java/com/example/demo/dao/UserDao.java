package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;

@Mapper
public interface UserDao extends BaseDao<User>{

    /***
     *  根据用户名查询系统用户
     * @param username
     * @return com.example.demo.entity.User
     * @author caog
     * @createTime 2020/5/8 21:29
     *
     */
    User queryByUserName(String username);

}  
