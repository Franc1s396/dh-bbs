package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.post.entity.Announcement;
import org.francis.dh.post.entity.vo.AnnouncementAddVo;
import org.francis.dh.post.entity.vo.AnnouncementQueryVo;
import org.francis.dh.post.entity.vo.AnnouncementUdpVo;
import org.francis.dh.post.service.AnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/announcement")
@Api(tags = "公告接口")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("")
    @ApiOperation(value = "查询发布的公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:get')")
    public RespResult getPlayAnnouncement(){
        LambdaQueryWrapper<Announcement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Announcement::getPlay,1);
        Announcement announcement = announcementService.getOne(queryWrapper);
        return RespResult.ok().data("announcement",announcement);
    }

    /**
     * 分页查询公告
     *
     * @param announcementQueryVo 查询参数
     * @return 公告信息
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:list')")
    public RespResult getAnnouncements(@Valid AnnouncementQueryVo announcementQueryVo) {
        Page<Announcement> announcementPage = new Page<>(announcementQueryVo.getPageNo(),
                announcementQueryVo.getPageSize());
        IPage<Announcement> announcements = announcementService.getAnnouncements(announcementPage, announcementQueryVo);
        return RespResult.ok().data("announcements", announcements);
    }

    /**
     * 添加公告
     *
     * @param announcementAddVo 添加参数
     * @return 添加结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:add')")
    public RespResult addAnnouncement(@Valid @RequestBody AnnouncementAddVo announcementAddVo) {
        Long userId = SecurityUtils.getUserId();
        Announcement announcement = new Announcement();
        announcement.setContent(announcementAddVo.getContent());
        announcement.setCreateUser(userId);
        if (announcementService.save(announcement)) {
            return RespResult.ok().message("添加成功");
        }
        return RespResult.error().message("添加失败,请重试");
    }

    /**
     * 更新公告
     *
     * @param announcementUdpVo 更新参数
     * @return 更新结果
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:update')")
    public RespResult updateAnnouncement(@Valid @RequestBody AnnouncementUdpVo announcementUdpVo) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementUdpVo, announcement);
        if (announcementService.updateById(announcement)) {
            return RespResult.ok().message("更新成功");
        }
        return RespResult.error().message("更新失败,请重试");
    }

    /**
     * 发布公告 启动首页轮播
     * @param id 公告编号
     * @return 发布结果
     */
    @PutMapping("/play/{id}")
    @ApiOperation(value = "发布公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:play')")
    public RespResult playAnnouncement(@PathVariable Long id) {
        if (announcementService.playAnnouncement(id)) {
            return RespResult.ok().message("发布成功");
        }
        return RespResult.error().message("发布失败，请重试");
    }

    /**
     * 删除公告
     * @param id 公告编号
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除公告")
    //@PreAuthorize("@Perms.hasPerm('admin:announcement:delete')")
    public RespResult deleteAnnouncement(@PathVariable Long id) {
        if (announcementService.removeById(id)) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

