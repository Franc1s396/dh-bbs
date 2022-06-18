package org.francis.dh.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.francis.dh.common.core.entity.Perms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
public interface PermsMapper extends BaseMapper<Perms> {

    List<Perms> getPermsByUid(@Param("userId") Long userId);
}
