package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.common.core.redis.CustomKeyGenerator;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.post.entity.Post;
import org.francis.dh.post.entity.dto.PostDto;
import org.francis.dh.post.entity.vo.PostAddVo;
import org.francis.dh.post.entity.vo.PostQueryVo;
import org.francis.dh.post.mapper.PostMapper;
import org.francis.dh.post.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    @Cacheable(cacheNames = "cache",keyGenerator = "customKeyGenerator")
    public IPage<PostDto> getPostPage(PostQueryVo postQueryVo) {
        Page<PostDto> postPage = new Page<>(postQueryVo.getPageNo(), postQueryVo.getPageSize());
        IPage<PostDto> postList = postMapper.getPostPage(postPage, postQueryVo);
        postList.getRecords().forEach(postDto -> {
            if (postDto.getContent().length()>=30) {
                postDto.setContent(postDto.getContent()+"...");
            }
        });
        return postList;
    }

    @Override
    public PostDto getPostById(Long id) {
        return postMapper.getPostById(id);
    }

    @Override
    public boolean addPost(PostAddVo postAddVo) {
        Long userId = SecurityUtils.getUserId();
        Post post = new Post();
        post.setUserId(userId);
        BeanUtils.copyProperties(postAddVo,post);
        return this.save(post);
    }
}
