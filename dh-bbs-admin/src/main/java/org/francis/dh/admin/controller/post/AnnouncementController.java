package org.francis.dh.admin.controller.post;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.vo.AnnouncementAddVo;
import org.francis.dh.post.entity.vo.AnnouncementQueryVo;
import org.francis.dh.post.entity.vo.AnnouncementUdpVo;
import org.francis.dh.post.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
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

    @GetMapping("/page")
    @ApiOperation(value = "分页查询公告")
    public RespResult getAnnouncements(@Valid AnnouncementQueryVo announcementQueryVo){
        return RespResult.ok();
    }

    @PostMapping("")
    @ApiOperation(value = "添加公告")
    public RespResult addAnnouncement(@Valid @RequestBody AnnouncementAddVo announcementAddVo){
        return RespResult.ok();
    }

    @PutMapping("")
    @ApiOperation(value = "更新公告")
    public RespResult updateAnnouncement(@Valid @RequestBody AnnouncementUdpVo announcementUdpVo){
        return RespResult.ok();
    }

    @DeleteMapping("")
    @ApiOperation(value = "删除公告")
    public RespResult deleteAnnouncement(){
        return RespResult.ok();
    }
}

