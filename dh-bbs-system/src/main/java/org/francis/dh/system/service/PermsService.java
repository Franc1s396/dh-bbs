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
}
