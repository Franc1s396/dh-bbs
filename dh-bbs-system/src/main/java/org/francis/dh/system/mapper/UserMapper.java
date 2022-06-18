package org.francis.dh.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.francis.dh.common.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(@Param("username") String username);
}
