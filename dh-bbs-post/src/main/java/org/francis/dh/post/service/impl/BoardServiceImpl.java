package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Board;
import org.francis.dh.post.entity.vo.BoardQueryVo;
import org.francis.dh.post.mapper.BoardMapper;
import org.francis.dh.post.service.BoardService;
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
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {

    @Override
    public IPage<Board> getBoards(Page<Board> boardPage, BoardQueryVo boardQueryVo) {
        return null;
    }
}
