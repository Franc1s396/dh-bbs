package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.BoardAudit;
import org.francis.dh.post.entity.dto.BoardAuditDto;
import org.francis.dh.post.entity.vo.BoardAuditQueryVo;
import org.francis.dh.post.service.BoardAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/board/audit")
@Api(tags = "板块审核接口")
public class BoardAuditController {
    @Autowired
    private BoardAuditService boardAuditService;

    @GetMapping("/page")
    //@PreAuthorize("@Perms.hasPerm('admin:board:audit:list')")
    @ApiOperation("分页查询板块审核")
    public RespResult getBoardAuditPage(BoardAuditQueryVo boardAuditQueryVo) {
        Page<BoardAuditDto> boardAuditPage = new Page<>(boardAuditQueryVo.getPageNo(), boardAuditQueryVo.getPageSize());
        IPage<BoardAuditDto> boardAuditList = boardAuditService.getBoardAuditPage(boardAuditPage, boardAuditQueryVo);
        return RespResult.ok().data("boardAuditList", boardAuditList);
    }

    @PutMapping("/approved/{id}")
    //@PreAuthorize("@Perms.hasPerm('admin:board:audit:approved')")
    @ApiOperation("通过审核")
    public RespResult approved(@RequestParam(name = "板块审核编号") @PathVariable Long id) {
        LambdaUpdateWrapper<BoardAudit> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BoardAudit::getId, id)
                .set(BoardAudit::getAuditStatus, 1);
        if (boardAuditService.update(updateWrapper)) {
            return RespResult.ok().message("通过成功");
        }
        return RespResult.error().message("通过失败，请重试");
    }

    @PutMapping("/reject/{id}")
    //@PreAuthorize("@Perms.hasPerm('admin:board:audit:approved')")
    @ApiOperation("拒绝通过审核")
    public RespResult reject(@RequestParam(name = "板块审核编号") @PathVariable Long id) {
        LambdaUpdateWrapper<BoardAudit> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BoardAudit::getId, id)
                .set(BoardAudit::getAuditStatus, 2);
        if (boardAuditService.update(updateWrapper)) {
            return RespResult.ok().message("拒绝成功");
        }
        return RespResult.error().message("拒绝失败，请重试");
    }
}

