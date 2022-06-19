package org.francis.dh.system.service.impl;

import org.francis.dh.common.constant.Constants;
import org.francis.dh.common.core.entity.LoginBody;
import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.redis.RedisCache;
import org.francis.dh.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@Service
public class LoginService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(LoginBody loginBody) {
        validateCaptcha(loginBody.getCode(),loginBody.getUuid());
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getEmail(), loginBody.getPassword()));
        } catch (Exception e) {
            if (e instanceof DisabledException) {
                throw new SecurityException("账户被禁用，请联系管理员!");
            } else if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                throw new SecurityException("用户名或者密码输入错误，请重新输入!");
            } else {
                e.printStackTrace();
            }
        }
        LoginUser user = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(user);
    }

    private void validateCaptcha(String code,String uuid){
        String verifyKey = Constants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new SecurityException("验证码请求有误");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new SecurityException("验证码错误");
        }
    }
}
