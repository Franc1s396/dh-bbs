package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.common.exception.ServiceException;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.post.entity.Board;
import org.francis.dh.post.entity.dto.BoardDto;
import org.francis.dh.post.entity.vo.BoardAddVo;
import org.francis.dh.post.entity.vo.BoardQueryVo;
import org.francis.dh.post.entity.vo.BoardUdpVo;
import org.francis.dh.post.mapper.BoardMapper;
import org.francis.dh.post.service.BoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public IPage<BoardDto> getBoards(Page<BoardDto> boardPage, BoardQueryVo boardQueryVo) {
        return boardMapper.getBoards(boardPage,boardQueryVo);
    }

    @Override
    public boolean addBoard(BoardAddVo boardAddVo) {
        LambdaQueryWrapper<Board> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Board::getName,boardAddVo.getName());
        if (this.getOne(queryWrapper)!=null) {
            throw new ServiceException("板块名字已存在");
        }
        Board board = new Board();
        board.setName(boardAddVo.getName());
        board.setCreateUser(SecurityUtils.getUserId());
        return this.save(board);
    }

    @Override
    public boolean updateBoard(BoardUdpVo boardUdpVo) {
        Board board = this.getById(boardUdpVo.getId());
        if (board==null) {
            throw new ServiceException("板块不存在");
        }
        if (!board.getName().equals(boardUdpVo.getName())) {
            LambdaQueryWrapper<Board> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Board::getName,boardUdpVo.getName());
            if (this.getOne(queryWrapper)!=null) {
                throw new ServiceException("板块名字已被占用");
            }
            BeanUtils.copyProperties(boardUdpVo,board);
            return this.updateById(board);
        }
        return false;
    }
}
