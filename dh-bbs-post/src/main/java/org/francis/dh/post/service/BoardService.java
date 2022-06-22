package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Board;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.dto.BoardDto;
import org.francis.dh.post.entity.vo.BoardAddVo;
import org.francis.dh.post.entity.vo.BoardQueryVo;
import org.francis.dh.post.entity.vo.BoardUdpVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface BoardService extends IService<Board> {

    /**
     * 分页查询板块
     * @param boardPage 分页参数
     * @param boardQueryVo 查询参数
     * @return 板块信息
     */
    IPage<BoardDto> getBoards(Page<BoardDto> boardPage, BoardQueryVo boardQueryVo);

    /**
     * 添加板块
     * @param boardAddVo 添加参数
     * @return 添加结果
     */
    boolean addBoard(BoardAddVo boardAddVo);

    /**
     * 更新板块
     * @param boardUdpVo 更新参数
     * @return 更新结果
     */
    boolean updateBoard(BoardUdpVo boardUdpVo);
}
