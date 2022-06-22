package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.BoardAudit;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.dto.BoardAuditDto;
import org.francis.dh.post.entity.vo.BoardAuditQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface BoardAuditService extends IService<BoardAudit> {

    IPage<BoardAuditDto> getBoardAuditPage(Page<BoardAuditDto> boardAuditPage, BoardAuditQueryVo boardAuditQueryVo);
}
