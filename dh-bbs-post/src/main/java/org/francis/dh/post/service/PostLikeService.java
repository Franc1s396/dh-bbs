package org.francis.dh.post.service;

import org.francis.dh.post.entity.PostLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-20
 */
public interface PostLikeService extends IService<PostLike> {

    Integer getPostLikeTotalByPid(Long postId);
}
