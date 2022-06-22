package org.francis.dh.post.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.francis.dh.post.entity.BoardAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.francis.dh.post.entity.dto.BoardAuditDto;
import org.francis.dh.post.entity.vo.BoardAuditQueryVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface BoardAuditMapper extends BaseMapper<BoardAudit> {

    IPage<BoardAuditDto> getBoardAuditPage (@Param("boardAuditPage") Page<BoardAuditDto> boardAuditPage,
                                            @Param("boardAuditQueryVo") BoardAuditQueryVo boardAuditQueryVo);
}
