package com.example.demo.service.impl;

import com.example.demo.dao.SysUserTokenDao;
import com.example.demo.entity.SysUserToken;
import com.example.demo.result.Result;
import com.example.demo.service.SysUserTokenService;
import com.example.demo.utils.TokenGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {
	@Resource
	private SysUserTokenDao sysUserTokenDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public SysUserToken queryByUserId(Long userId) {
		return sysUserTokenDao.queryByUserId(userId);
	}

	@Override
	public void save(SysUserToken token){
		sysUserTokenDao.save(token);
	}
	
	@Override
	public void update(SysUserToken token){
		sysUserTokenDao.update(token);
	}

	@Override
	public Result createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserToken tokenEntity = queryByUserId(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserToken();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			update(tokenEntity);
		}

		Map<String,Object> map = new HashMap<>();
		map.put("token",token);
		map.put("expire",EXPIRE);

		return Result.success(map);
	}
}
