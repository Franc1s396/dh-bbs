package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.Comment;
import org.francis.dh.post.entity.dto.CommentDto;
import org.francis.dh.post.entity.vo.CommentQueryVo;
import org.francis.dh.post.service.CommentService;
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
@RequestMapping("/comment")
@Api(tags = "评论接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 分页查询评论
     * @param commentQueryVo 查询参数
     * @return 评论列表
     */
    @GetMapping("/page")
    //@PreAuthorize("@Perms.hasPerm('admin:comment:list')")
    @ApiOperation("分页查询评论")
    public RespResult getCommentByPId(CommentQueryVo commentQueryVo){
        IPage<CommentDto> commentList=commentService.getCommentByPId(commentQueryVo);
        return RespResult.ok().data("commentList",commentList);
    }

    @GetMapping("/total")
    @ApiOperation("帖子评论总数")
    public RespResult getCommentTotalByPid(Long postId){
        Integer count=commentService.getCommentTotalByPid(postId);
        return RespResult.ok().data("count",count);
    }

    @DeleteMapping("/{commentId}")
    //@PreAuthorize("@Perms.hasPerm('admin:comment:delete')")
    @ApiOperation("删除评论")
    public RespResult deleteComment(@RequestParam(name = "评论编号")@PathVariable Long commentId){
        if (commentService.removeById(commentId)) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

