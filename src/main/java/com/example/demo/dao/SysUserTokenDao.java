package com.example.demo.dao;

import com.example.demo.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 系统用户Token Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserToken> {
    
    SysUserToken queryByUserId(Long userId);

    SysUserToken queryByToken(String token);
	
}
