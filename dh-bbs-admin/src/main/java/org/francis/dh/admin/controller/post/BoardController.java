package org.francis.dh.admin.controller.post;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.vo.BoardAddVo;
import org.francis.dh.post.entity.vo.BoardUdpVo;
import org.francis.dh.post.service.BoardService;
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
@RequestMapping("/board")
@Api(tags = "板块接口")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("")
    @ApiOperation(value = "分页查询板块")
    public RespResult getBoards(){
        return RespResult.ok();
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

