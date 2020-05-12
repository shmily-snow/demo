package com.example.demo.service;

import com.example.demo.entity.SysUserToken;
import com.example.demo.result.Result;

/**
 * @Description: 系统用户token
 * @author: kylin
 * @create: 2018-01-30 11:15
 **/
public interface SysUserTokenService {

    SysUserToken queryByUserId(Long userId);

    void save(SysUserToken token);

    void update(SysUserToken token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    Result createToken(long userId);
}
