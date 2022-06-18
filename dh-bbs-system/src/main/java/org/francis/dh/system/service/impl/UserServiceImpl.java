package org.francis.dh.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.common.exception.ServiceException;
import org.francis.dh.common.utils.StringUtils;
import org.francis.dh.system.mapper.UserMapper;
import org.francis.dh.system.service.PermsService;
import org.francis.dh.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermsService permsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.getUserByUsername(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("用户名或密码不正确");
        } else if (user.getUserEnabled()==1) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(User user) {
        return new LoginUser(user.getId(), user, permsService.getPermissions(user));
    }
}
