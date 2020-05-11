package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.SysMenu;

/**
 * @Description: 菜单管理Dao组件
 * @author: kylin
 * @create: 2018-01-30 11:34
 **/
@Mapper
public interface SysMenuDao extends BaseDao<SysMenu> {
	
}
