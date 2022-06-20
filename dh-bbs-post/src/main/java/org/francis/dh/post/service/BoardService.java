package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Board;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.vo.BoardQueryVo;

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
    IPage<Board> getBoards(Page<Board> boardPage, BoardQueryVo boardQueryVo);
}
