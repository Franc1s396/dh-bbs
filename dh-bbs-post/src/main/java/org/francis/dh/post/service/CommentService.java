package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.dto.CommentDto;
import org.francis.dh.post.entity.vo.CommentQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据id分页查询帖子评论
     * @param commentQueryVo 查询参数
     * @return 评论信息
     */
    IPage<CommentDto> getCommentByPId(CommentQueryVo commentQueryVo);
}
