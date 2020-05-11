package com.example.demo.utils.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Constant;
import com.example.demo.dao.SysMenuDao;
import com.example.demo.dao.SysUserTokenDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysUserToken;
import com.example.demo.entity.User;
import com.example.demo.utils.shiro.service.ShiroService;

/**
 * @Description:
 * @author: kylin
 * @create: 2018-01-31 10:07
 **/
@Service
public class ShiroServiceImpl implements ShiroService {
    @Resource
    private UserDao sysUserDao;
    @Resource
    private SysUserTokenDao sysUserTokenDao;
    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public User queryUser(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        // 系统管理员，拥有最高权限
        // if(userId == Constant.SUPER_ADMIN){
        List<SysMenu> menuList = sysMenuDao.queryList(new HashMap<>());
        permsList = new ArrayList<>(menuList.size());
        for (SysMenu menu : menuList) {
            permsList.add(menu.getPermission());
        }
        // }else{
        // permsList = sysUserDao.queryAllPerms(userId);
        // }
        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }
}
