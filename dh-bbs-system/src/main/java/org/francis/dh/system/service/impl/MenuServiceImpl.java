package org.francis.dh.system.service.impl;

import org.francis.dh.common.core.entity.Menu;
import org.francis.dh.system.mapper.MenuMapper;
import org.francis.dh.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenusByUserId(Long userId) {
        List<Menu> menus=menuMapper.getMenusByUserId(userId);
        return menus.stream().filter(menu -> menu.getParentId() == 0)
                .peek(menu -> menu.setChildren(getChildren(menu, menus))
                ).collect(Collectors.toList());
    }

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param all  所有节点
     * @return 根节点信息
     */
    private List<Menu> getChildren(Menu root, List<Menu> all) {
        return all.stream().filter(menu -> {
            return Objects.equals(menu.getParentId(), root.getId());
        }).peek(menu -> menu.setChildren(getChildren(menu, all))
        ).collect(Collectors.toList());
    }
}
