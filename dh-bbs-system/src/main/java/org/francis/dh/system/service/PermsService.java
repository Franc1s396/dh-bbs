package org.francis.dh.system.service;

import org.francis.dh.common.core.entity.Perms;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.common.core.entity.User;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
public interface PermsService extends IService<Perms> {

    /**
     * 根据用户编号获取权限
     * @param userId 用户编号
     * @return 权限列表
     */
    List<Perms> getPermsByUid(Long userId);

    /**
     * 根据用户获取用户权限
     * @param user 用户
     * @return 权限集合
     */
    Set<String> getPermissions(User user);


    /**
     * 验证用户是否具备某权限
     * @param permission 权限名字
     * @return
     */
    Boolean hasPerm(String permission);

    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    Boolean hasPermissions(Set<String> permissions, String permission);

}
