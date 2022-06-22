package org.francis.dh.post.service.impl;

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
    public IPage<CommentDto> getCommentByPId(Page<CommentDto> commentPage, CommentQueryVo commentQueryVo) {
        return commentMapper.getCommentByPId(commentPage,commentQueryVo);
    }
}
