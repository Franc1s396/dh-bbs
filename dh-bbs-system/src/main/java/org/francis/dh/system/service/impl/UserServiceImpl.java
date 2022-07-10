package org.francis.dh.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.common.exception.ServiceException;
import org.francis.dh.common.utils.StringUtils;
import org.francis.dh.system.entity.vo.UserQueryVo;
import org.francis.dh.system.mapper.UserMapper;
import org.francis.dh.system.service.PermsService;
import org.francis.dh.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
            throw new UsernameNotFoundException("用户名或者密码输入错误，请重新输入!");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(User user) {
        return new LoginUser(user.getId(), user, permsService.getPermissions(user));
    }

    @Override
    public IPage<User> getUsersPage(UserQueryVo userQueryVo) {
        Page<User> userPage = new Page<>(userQueryVo.getPageNo(), userQueryVo.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User user = new User();
        BeanUtils.copyProperties(userQueryVo,user);
        queryWrapper.setEntity(user);
        /*queryWrapper.eq(User::getId,userQueryVo.getId())
                .like(User::getNickName,userQueryVo.getNickName())
                .eq(User::getEmail,userQueryVo.getEmail())
                .eq(User::getPhone,userQueryVo.getPhone())
                .eq(User::getSex,userQueryVo.getSex());*/
        return this.page(userPage, queryWrapper);
    }
}
