package com.example.demo.serviceTest;

import com.example.demo.baseTest.SpringTestCase;
import com.example.demo.utils.RedisUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @projectName: peixun
 * @author: caog
 * @date: 2020年05月11日 21:48
 * @version: 1.0
 */
public class RedisTest extends SpringTestCase {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key4", "redis_vale5");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key4");
        System.out.println(value);
    }
}
