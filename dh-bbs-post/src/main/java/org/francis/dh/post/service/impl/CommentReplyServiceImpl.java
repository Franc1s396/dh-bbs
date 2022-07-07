package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.common.core.redis.CustomKeyGenerator;
import org.francis.dh.post.entity.CommentReply;
import org.francis.dh.post.entity.vo.CommentReplyQueryVo;
import org.francis.dh.post.mapper.CommentReplyMapper;
import org.francis.dh.post.service.CommentReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {
    @Override
    @Cacheable(cacheNames = "cache",keyGenerator = "customKeyGenerator")
    public IPage<CommentReply> getCommentReplyPage(CommentReplyQueryVo commentReplyQueryVo) {
        Page<CommentReply> commentReplyPage = new Page<>(commentReplyQueryVo.getPageNo(),
                commentReplyQueryVo.getPageSize());
        LambdaQueryWrapper<CommentReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentReply::getCommentId, commentReplyQueryVo.getCommentId());
        return this.page(commentReplyPage, queryWrapper);
    }
}
