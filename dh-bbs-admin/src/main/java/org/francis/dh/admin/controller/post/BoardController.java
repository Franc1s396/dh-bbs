package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.post.entity.Board;
import org.francis.dh.post.entity.dto.BoardDto;
import org.francis.dh.post.entity.vo.BoardAddVo;
import org.francis.dh.post.entity.vo.BoardQueryVo;
import org.francis.dh.post.entity.vo.BoardUdpVo;
import org.francis.dh.post.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/board")
@Api(tags = "板块接口")
public class BoardController {
    @Autowired
    private BoardService boardService;

    /**
     * 分页查询板块
     * @param boardQueryVo 查询参数
     * @return 板块信息
     */
    @GetMapping("/page")
    //@PreAuthorize("@Perms.hasPerm('admin:board:list')")
    @ApiOperation(value = "分页查询板块")
    public RespResult getBoards(@Valid BoardQueryVo boardQueryVo){
        Page<BoardDto> boardPage = new Page<>(boardQueryVo.getPageNo(), boardQueryVo.getPageSize());
        IPage<BoardDto> boards=boardService.getBoards(boardPage,boardQueryVo);
        return RespResult.ok().data("boards",boards);
    }

    /**
     * 添加板块
     * @param boardAddVo 添加参数
     * @return 添加结果
     */
    @PostMapping("")
    //@PreAuthorize("@Perms.hasPerm('admin:board:add')")
    @ApiOperation(value = "添加板块")
    public RespResult addBoard(@Valid @RequestBody BoardAddVo boardAddVo){
        if (boardService.addBoard(boardAddVo)) {
            return RespResult.ok().message("添加成功");
        }
        return RespResult.error().message("添加失败");
    }

    /**
     * 更新板块
     * @param boardUdpVo 更新参数
     * @return 更新结果
     */
    @PutMapping("")
    //@PreAuthorize("@Perms.hasPerm('admin:board:update')")
    @ApiOperation(value = "更新板块")
    public RespResult updateBoard(@Valid @RequestBody BoardUdpVo boardUdpVo){
        if (boardService.updateBoard(boardUdpVo)){
            return RespResult.ok().message("更新成功");
        }
        return RespResult.error().message("更新失败,请检查是否填写更新参数");
    }

    /**
     * 删除板块
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    //@PreAuthorize("@Perms.hasPerm('admin:board:delete')")
    @ApiOperation(value = "删除板块")
    public RespResult deleteBoard(@PathVariable @RequestParam(name = "板块编号") Long id){
        if (boardService.removeById(id)) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请检查是否存在此板块");
    }

    /**
     * 批量删除板块
     * @param ids 参数
     * @return 删除结果
     */
    @DeleteMapping("")
    //@PreAuthorize("@Perms.hasPerm('admin:board:delete')")
    @ApiOperation(value = "批量删除板块")
    public RespResult deleteBatchBoard(@RequestParam(name = "板块编号")Long[] ids){
        if (boardService.removeByIds(Arrays.asList(ids))) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

