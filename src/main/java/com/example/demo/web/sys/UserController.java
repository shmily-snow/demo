package com.example.demo.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.result.enums.ResultCode;
import com.example.demo.service.UserService;
import com.example.demo.utils.BeanMap;
import com.example.demo.utils.Query;
import com.example.demo.web.BaseController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /***
     * 获取用户列表
     * 
     * @param query
     * @return com.example.demo.result.Result
     * @author caog
     * @createTime 2020/5/8 22:02
     *
     */
    @GetMapping(value = "/list")
    @RequiresPermissions("sys:user:list")
    public Result list(Query query) {
        LOGGER.info("demo 获取用户列表");
        long startClient = System.currentTimeMillis();

        Map<String, Object> conditionMap = BeanMap.objectToMap(query);

        List<User> userList = userService.queryList(conditionMap);

        Map<String, Object> map = new HashMap<>();
        map.put("list", userList);

        long endClient = System.currentTimeMillis();
        LOGGER.info("demo 查询结束,返回值:{},[耗时:{}毫秒]", 1, (endClient - startClient));
        return Result.success(map);
    }

    /**
     * 根据用户UserId查询用户信息
     * 
     * @param userId
     * @return
     * @author:caog
     * @createTime:2020年5月9日 下午3:14:45
     */
    @PostMapping("/info")
    @RequiresPermissions("sys:user:info")
    public Result info(Long userId) {
        User user = userService.queryObject(userId);
        return Result.success(user);
    }

    /**
     * 保存用户
     * 
     * @param user
     * @return
     * @author:caog
     * @createTime:2020年5月9日 下午3:25:11
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(User user) {
        user.setCreateUserId(getUserId());
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(User user) {

        if (0 == user.getUserId()) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }

        userService.update(user);

        return Result.success();
    }
}
