package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.BoardAudit;
import org.francis.dh.post.entity.dto.BoardAuditDto;
import org.francis.dh.post.entity.vo.BoardAuditQueryVo;
import org.francis.dh.post.mapper.BoardAuditMapper;
import org.francis.dh.post.service.BoardAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BoardAuditServiceImpl extends ServiceImpl<BoardAuditMapper, BoardAudit> implements BoardAuditService {

    @Autowired
    private BoardAuditMapper boardAuditMapper;

    @Override
    public IPage<BoardAuditDto> getBoardAuditPage(Page<BoardAuditDto> boardAuditPage, BoardAuditQueryVo boardAuditQueryVo) {
        return boardAuditMapper.getBoardAuditPage(boardAuditPage,boardAuditQueryVo);
    }
}
