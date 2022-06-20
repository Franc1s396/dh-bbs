package org.francis.dh.post.service.impl;

import org.francis.dh.post.entity.Post;
import org.francis.dh.post.mapper.PostMapper;
import org.francis.dh.post.service.PostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
