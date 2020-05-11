package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import com.example.demo.annotation.DataFilter;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User queryByUserName(String username) {
        return userDao.queryByUserName(username);
    }

    @Override
    @DataFilter(tableAlias = "u", user = false)
    public List<User> queryList(Map<String, Object> map) {
        return userDao.queryList(map);
    }

    @Override
    @DataFilter(tableAlias = "u", user = false)
    public int queryTotal(Map<String, Object> map) {
        return userDao.queryTotal(map);
    }

    @Override
    public User queryObject(Long userId) {
        return userDao.queryObject(userId);
    }

    @Override
    public void save(User user) {
        user.setCreateTime(new Date());
        // sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        userDao.save(user);
    }

    @Override
    public int update(User user) {
        int result = 0;
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        result = userDao.update(user);

        return result;
    }
}
