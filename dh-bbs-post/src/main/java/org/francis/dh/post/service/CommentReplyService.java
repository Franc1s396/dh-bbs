package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.CommentReply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.vo.CommentReplyQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface CommentReplyService extends IService<CommentReply> {

    IPage<CommentReply> getCommentReplyPage(CommentReplyQueryVo commentReplyQueryVo);
}
