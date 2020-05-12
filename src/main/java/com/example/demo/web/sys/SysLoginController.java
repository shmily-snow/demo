package com.example.demo.web.sys;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.result.Result;
import com.example.demo.service.SysUserTokenService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ShiroUtils;
import com.example.demo.web.BaseController;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登录Controller
 *
 * @version 1.0
 * @projectName:demo
 * @author:caog
 * @date:2020年5月8日 下午3:33:07
 */
@RestController
@Api(tags = "登录处理")
public class SysLoginController extends BaseController {

    @Autowired
    private Producer producer;

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 获取图文验证码
     *
     * @param response
     * @throws ServletException
     * @throws IOException
     * @author:caog
     * @createTime:2020年5月8日 下午3:36:46
     */
    @RequestMapping("captcha.jpg")
    @ApiOperation(value = "获取图文验证码接口", notes = "获取图文验证码接口处理")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/sys/login")
    public Result login( String username, String password, String captcha) throws IOException {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return Result.failure("验证码不正确");
        }
        //用户信息
        User user = userService.queryByUserName(username);

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return Result.failure("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return Result.failure("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Result result = sysUserTokenService.createToken(user.getUserId());
        return result;
    }

}
