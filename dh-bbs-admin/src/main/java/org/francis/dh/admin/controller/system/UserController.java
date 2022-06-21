package org.francis.dh.admin.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.system.entity.vo.UserAddVo;
import org.francis.dh.system.entity.vo.UserQueryVo;
import org.francis.dh.system.entity.vo.UserUpdateVo;
import org.francis.dh.system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 分页查询用户信息
     * @param userQueryVo 用户查询参数
     * @return 查询结果
     */
    @GetMapping("/page")
    @PreAuthorize("@Perms.hasPerm('admin:user:list')")
    @ApiOperation(value = "分页查询用户信息")
    public RespResult getUsers(@Valid UserQueryVo userQueryVo){
        return RespResult.ok();
    }

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("@Perms.hasPerm('admin:user:id')")
    @ApiOperation(value = "根据id获取用户")
    public RespResult getUserByUid(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return RespResult.error().message("查无此用户");
        }
        return RespResult.ok().data("user", user);
    }

    /**
     * 根据邮箱获取用户
     * @param email 邮箱
     * @return 用户信息
     */
    @GetMapping("/")
    @PreAuthorize("@Perms.hasPerm('admin:user:email')")
    @ApiOperation(value = "根据邮箱获取用户")
    public RespResult getUserByUid(@RequestParam(value = "邮箱") String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return RespResult.error().message("查无此用户");
        }
        return RespResult.ok().data("user", user);
    }

    /**
     * 添加用户
     * @param userAddVo 用户添加参数
     * @return 添加结果
     */
    @PostMapping("/add")
    @PreAuthorize("@Perms.hasPerm('admin:user:add')")
    @ApiOperation(value = "添加用户")
    public RespResult addUser(@Valid @RequestBody UserAddVo userAddVo) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, userAddVo.getEmail());
        User verifyUsernameUser = userService.getOne(queryWrapper);
        if (verifyUsernameUser != null) {
            return RespResult.error().message("该用户名已被占用");
        }
        queryWrapper.clear();
        if (userAddVo.getPhone() != null) {
            queryWrapper.eq(User::getPhone, userAddVo.getPhone());
            User verifyPhoneUser = userService.getOne(queryWrapper);
            if (verifyPhoneUser != null) {
                return RespResult.error().message("该手机号已被占用");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(userAddVo, user);
        user.setPassword(passwordEncoder.encode("123456"));
        boolean result = userService.save(user);
        if (result) {
            return RespResult.ok().message("添加成功");
        }
        return RespResult.error().message("添加失败，请重试");
    }

    /**
     * 更新用户信息
     * @param userUpdateVo 用户更新参数
     * @return 更新结果
     */
    @PutMapping("/update")
    @PreAuthorize("@Perms.hasPerm('admin:user:update')")
    @ApiOperation(value = "更新用户信息")
    public RespResult updateUser(@Valid @RequestBody UserUpdateVo userUpdateVo) {
        User user = userService.getById(userUpdateVo.getId());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (!userUpdateVo.getEmail().equals(user.getEmail())) {
            queryWrapper.eq(User::getEmail, userUpdateVo.getEmail());
            User verifyUsernameUser = userService.getOne(queryWrapper);
            if (verifyUsernameUser != null) {
                return RespResult.error().message("该用户名已被占用");
            }
        }
        queryWrapper.clear();
        if (!userUpdateVo.getPhone().equals(user.getPhone())) {
            queryWrapper.eq(User::getPhone, userUpdateVo.getPhone());
            User verifyPhoneUser = userService.getOne(queryWrapper);
            if (verifyPhoneUser != null) {
                return RespResult.error().message("该手机号已被占用");
            }
        }
        BeanUtils.copyProperties(userUpdateVo, user);
        boolean result = userService.updateById(user);
        if (result) {
            return RespResult.ok().message("更新成功");
        }
        return RespResult.error().message("更新失败，请重试");
    }

    /**
     * 封禁用户
     * @param id 用户id
     * @return 封禁结果
     */
    @PutMapping("/ban/{id}")
    @PreAuthorize("@Perms.hasPerm('admin:user:ban')")
    @ApiOperation(value = "根据id封禁用户")
    public RespResult banUserByUid(@PathVariable Long id) {
        User verifyBanUser = userService.getById(id);
        if (verifyBanUser.getUserEnabled()==1) {
            return RespResult.error().message("该用户已被封禁");
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, id)
                .set(User::getUserEnabled, true);
        boolean result = userService.update(updateWrapper);
        if (result) {
            return RespResult.ok().message("封禁成功");
        }
        return RespResult.error().message("封禁失败，请重试");
    }

    /**
     * 恢复用户
     * @param id 用户id
     * @return 恢复结果
     */
    @PutMapping("/unblock/{id}")
    @PreAuthorize("@Perms.hasPerm('admin:user:unblock')")
    @ApiOperation(value = "根据id恢复用户")
    public RespResult unblockUserByUid(@PathVariable Long id) {
        User verifyBanUser = userService.getById(id);
        if (verifyBanUser.getUserEnabled()==0) {
            return RespResult.error().message("该用户未被封禁");
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, id)
                .set(User::getUserEnabled, false);
        boolean result = userService.update(updateWrapper);
        if (result) {
            return RespResult.ok().message("解封成功");
        }
        return RespResult.error().message("解封失败，请重试");
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@Perms.hasPerm('admin:user:delete')")
    @ApiOperation(value = "根据id删除用户")
    public RespResult deleteUserByUid(@PathVariable Long id) {
        boolean result = userService.removeById(id);
        if (result) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

