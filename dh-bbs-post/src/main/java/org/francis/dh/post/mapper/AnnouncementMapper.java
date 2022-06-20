package org.francis.dh.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.francis.dh.post.entity.Announcement;
import org.francis.dh.post.entity.vo.AnnouncementQueryVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    IPage<Announcement> getAnnouncements(@Param("announcementPage") Page<Announcement> announcementPage, @Param("announcementQueryVo") AnnouncementQueryVo announcementQueryVo);
}
