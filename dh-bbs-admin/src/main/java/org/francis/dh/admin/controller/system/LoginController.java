package org.francis.dh.admin.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.annotation.Log;
import org.francis.dh.common.constant.Constants;
import org.francis.dh.common.core.entity.LoginBody;
import org.francis.dh.common.core.entity.RegisterBody;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.common.core.redis.RedisCache;
import org.francis.dh.common.enums.BusinessType;
import org.francis.dh.common.enums.OperatorType;
import org.francis.dh.system.service.UserService;
import org.francis.dh.system.service.impl.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@RestController
@Api(tags = "登录相关接口")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登录
     * @param loginBody 登录参数
     * @return 登录结果 token
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public RespResult login(@Valid @RequestBody LoginBody loginBody){
        String token=loginService.login(loginBody);
        return RespResult.ok().data("token",token);
    }

    /**
     * 验证码
     * @param response 响应类
     * @param uuid 唯一标识
     */
    @GetMapping("/captcha")
    @ApiOperation(value = "验证码", produces = "image/jpeg")
    public void getCaptcha(@ApiIgnore HttpServletResponse response, String uuid) {
        response.setContentType("image/jpeg");
        //创建验证码文本
        String text = producer.createText();
        //验证码reids的key
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //保存到redis中
        redisCache.setCacheObject(verifyKey, text, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
