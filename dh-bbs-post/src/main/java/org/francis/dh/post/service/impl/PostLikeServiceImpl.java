package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.francis.dh.post.constant.PostConstants;
import org.francis.dh.post.entity.PostLike;
import org.francis.dh.post.mapper.PostLikeMapper;
import org.francis.dh.post.service.PostLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-06-20
 */
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike> implements PostLikeService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Integer getPostLikeTotalByPid(Long postId) {
        int total=0;
        //查询redis中未刷新回数据库的
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Integer counter = (Integer) opsForValue.get(PostConstants.POST_COUNTER_KEY + postId);
        total+=(counter==null?0:counter);
        //查询已经刷新回数据库的
        LambdaQueryWrapper<PostLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostLike::getPostId,postId);
        PostLike postLike = this.getOne(queryWrapper);
        if (postLike!=null){
            total+=postLike.getPostLike();
        }
        return total;
    }
}
