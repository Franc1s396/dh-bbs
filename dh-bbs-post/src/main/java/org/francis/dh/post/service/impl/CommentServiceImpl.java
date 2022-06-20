package org.francis.dh.post.service.impl;

import org.francis.dh.post.entity.Comment;
import org.francis.dh.post.mapper.CommentMapper;
import org.francis.dh.post.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
