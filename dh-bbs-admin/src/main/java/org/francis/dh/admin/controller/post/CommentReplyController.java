package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.entity.CommentReply;
import org.francis.dh.post.entity.dto.CommentReplyDto;
import org.francis.dh.post.entity.vo.CommentReplyQueryVo;
import org.francis.dh.post.service.CommentReplyService;
import org.francis.dh.post.service.CommentService;
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
@RequestMapping("/comment/reply")
@Api(tags = "评论回复接口")
public class CommentReplyController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * 分页查询评论回复
     * @param commentReplyQueryVo 查询参数
     * @return 评论回复列表
     */
    @GetMapping("/page")
    //@PreAuthorize("@Perms.hasPerm('admin:comment:reply:list')")
    @ApiOperation("分页查询评论回复")
    public RespResult getCommentReplyPage(CommentReplyQueryVo commentReplyQueryVo) {

        IPage<CommentReply> commentReplyList = commentReplyService
                .getCommentReplyPage(commentReplyQueryVo);
        return RespResult.ok().data("commentReplyList", commentReplyList);
    }


    @DeleteMapping("/{id}")
    //@PreAuthorize("@Perms.hasPerm('admin:comment:reply:delete')")
    @ApiOperation("删除评论回复")
    public RespResult deleteCommentReply(@RequestParam(name = "回复编号")@PathVariable Long id){
        if (commentReplyService.removeById(id)) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

