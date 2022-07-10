package org.francis.dh.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.francis.dh.common.core.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.system.entity.vo.UserQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
public interface UserService extends IService<User> {

    IPage<User> getUsersPage(UserQueryVo userQueryVo);
}
