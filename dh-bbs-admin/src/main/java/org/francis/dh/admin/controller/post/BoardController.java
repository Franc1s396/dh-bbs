package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.Board;
import org.francis.dh.post.entity.vo.BoardAddVo;
import org.francis.dh.post.entity.vo.BoardQueryVo;
import org.francis.dh.post.entity.vo.BoardUdpVo;
import org.francis.dh.post.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation(value = "分页查询板块")
    public RespResult getBoards(@Valid BoardQueryVo boardQueryVo){
        Page<Board> boardPage = new Page<>(boardQueryVo.getPageNo(), boardQueryVo.getPageSize());
        IPage<Board> boards=boardService.getBoards(boardPage,boardQueryVo);
        return RespResult.ok().data("boards",boards);
    }

    @PostMapping("")
    @ApiOperation(value = "添加板块")
    public RespResult addBoard(@Valid @RequestBody BoardAddVo boardAddVo){
        return RespResult.ok();
    }

    @PutMapping("")
    @ApiOperation(value = "更新板块")
    public RespResult updateBoard(@Valid @RequestBody BoardUdpVo boardUdpVo){
        return RespResult.ok();
    }

    @DeleteMapping("")
    @ApiOperation(value = "删除板块")
    public RespResult deleteBoard(){
        return RespResult.ok();
    }
}

