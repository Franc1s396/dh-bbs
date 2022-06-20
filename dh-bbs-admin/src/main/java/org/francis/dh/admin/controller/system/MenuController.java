package org.francis.dh.admin.controller.system;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.Menu;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单
     * @return 菜单
     */
    @GetMapping("/")
    @ApiOperation(value = "获取菜单")
    public RespResult getMenus(){
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus=menuService.getMenusByUserId(userId);
        return RespResult.ok().data("menus",menus);
    }
}

