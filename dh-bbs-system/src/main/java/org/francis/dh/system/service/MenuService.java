package org.francis.dh.system.service;

import org.francis.dh.common.core.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户获取对应菜单
     * @param userId 用户编号
     * @return 菜单
     */
    List<Menu> getMenusByUserId(Long userId);
}
