package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.dto.PostDto;
import org.francis.dh.post.entity.vo.PostAddVo;
import org.francis.dh.post.entity.vo.PostQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface PostService extends IService<Post> {

    /**
     * 分页查询帖子
     * @param postQueryVo 查询参数
     * @return 帖子列表
     */
    IPage<PostDto> getPostPage(PostQueryVo postQueryVo);

    /**
     * 根据id查询帖子
     * @param id 帖子编号
     * @return 帖子信息
     */
    PostDto getPostById(Long id);

    /**
     * 添加帖子
     * @param postAddVo 帖子添加参数
     * @return 添加结果
     */
    boolean addPost(PostAddVo postAddVo);
}
