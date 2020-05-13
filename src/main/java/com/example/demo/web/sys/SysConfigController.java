package com.example.demo.web.sys;

import com.example.demo.result.Result;
import com.example.demo.utils.RedisUtils;
import com.example.demo.web.BaseController;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
public class SysConfigController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysConfigController.class);

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/getValue")
    public Result info(@RequestParam(value = "keyName") @NotBlank(message = "keyName不能为空") String keyName) {
        String keyValue = redisUtils.get(keyName);
        return Result.success(keyValue);
    }

    @PostMapping("/save")
    public Result save(@RequestParam(value = "keyName") @NotBlank(message = "keyName不能为空") String keyName,
                       @RequestParam(value = "keyValue") @NotBlank(message = "keyValue不能为空") String keyValue) {
        redisUtils.set(keyName, keyValue);
        return Result.success();
    }

}
