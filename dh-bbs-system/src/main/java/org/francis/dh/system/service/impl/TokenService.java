package org.francis.dh.system.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.fileupload.RequestContext;
import org.francis.dh.common.constant.Constants;
import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.common.core.redis.RedisCache;
import org.francis.dh.common.exception.ServiceException;
import org.francis.dh.common.utils.StringUtils;
import org.francis.dh.common.utils.UUIDUtils;
import org.francis.dh.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@Service
public class TokenService {
    /**
     * 令牌自定义标识
     */
    @Value("${token.header}")
    private String header;

    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 令牌有效期（默认24小时）
     */
    @Value("${token.expireTime}")
    private Integer expireTime;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                Claim claim = decodedJWT.getClaim(Constants.LOGIN_USER_KEY);
                String uuid = claim.asString();
                String userKey = Constants.LOGIN_USER_KEY + uuid;
                return redisCache.getCacheObject(userKey);
            } catch (Exception e) {
                throw new ServiceException("请求信息出错");
            }
        }
        return null;
    }


    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        String uuid = UUIDUtils.get32UUID();
        loginUser.setToken(uuid);
        setUserAgent(loginUser);
        refreshToken(loginUser);
        User user = userService.getById(loginUser.getUserId());
        user.setLoginDate(LocalDateTime.now());
        user.setLoginIp(loginUser.getIpaddr());
        userService.updateById(user);
        return JWT.create()
                .withSubject(loginUser.getUsername())
                .withClaim(Constants.LOGIN_USER_KEY, uuid)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 60 * 1000))
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= 20 * 60 * 1000L) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * 60 * 1000);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        loginUser.getUser().setPassword(null);
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        loginUser.setIpaddr(ipAddress);
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }
}
