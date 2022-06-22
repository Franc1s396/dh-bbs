package org.francis.dh.post.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.francis.dh.post.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.francis.dh.post.entity.dto.CommentDto;
import org.francis.dh.post.entity.vo.CommentQueryVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface CommentMapper extends BaseMapper<Comment> {

    IPage<CommentDto> getCommentByPId(@Param("commentPage") Page<CommentDto> commentPage,
                                      @Param("commentQueryVo") CommentQueryVo commentQueryVo);
}
