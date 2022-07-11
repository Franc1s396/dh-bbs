package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Comment;
import org.francis.dh.post.entity.dto.CommentDto;
import org.francis.dh.post.entity.vo.CommentQueryVo;
import org.francis.dh.post.mapper.CommentMapper;
import org.francis.dh.post.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentDto> getCommentByPId(CommentQueryVo commentQueryVo) {
        Page<CommentDto> commentPage = new Page<>(commentQueryVo.getPageNo(), commentQueryVo.getPageSize());
        return commentMapper.getCommentByPId(commentPage,commentQueryVo);
    }

    @Override
    public Integer getCommentTotalByPid(Long postId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getPostId,postId);
        return this.count(queryWrapper);
    }
}
