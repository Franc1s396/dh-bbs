package org.francis.dh.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.francis.dh.post.entity.Post;
import org.francis.dh.post.entity.dto.PostDto;
import org.francis.dh.post.entity.vo.PostQueryVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface PostMapper extends BaseMapper<Post> {

    IPage<PostDto> getPostPage(@Param("postPage") Page<PostDto> postPage,
                               @Param("postQueryVo") PostQueryVo postQueryVo);

    PostDto getPostById(@Param("id") Long id);
}
