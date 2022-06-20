package org.francis.dh.post.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.post.entity.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.dh.post.entity.vo.AnnouncementQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface AnnouncementService extends IService<Announcement> {

    /**
     * 分页查询公告信息
     * @param announcementPage 分页参数
     * @param announcementQueryVo 查询参数
     * @return 公告信息
     */
    IPage<Announcement> getAnnouncements(Page<Announcement> announcementPage,
                                         AnnouncementQueryVo announcementQueryVo);

    /**
     * 发布公告
     * @param id 公告编号
     * @return 发布结果
     */
    boolean playAnnouncement(Long id);
}
