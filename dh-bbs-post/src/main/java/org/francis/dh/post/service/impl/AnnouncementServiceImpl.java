package org.francis.dh.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.dh.common.exception.ServiceException;
import org.francis.dh.post.entity.Announcement;
import org.francis.dh.post.entity.vo.AnnouncementQueryVo;
import org.francis.dh.post.mapper.AnnouncementMapper;
import org.francis.dh.post.service.AnnouncementService;
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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public IPage<Announcement> getAnnouncements(Page<Announcement> announcementPage,
                                                AnnouncementQueryVo announcementQueryVo) {
        return announcementMapper.getAnnouncements(announcementPage,announcementQueryVo);
    }

    @Override
    public boolean playAnnouncement(Long id) {
        LambdaQueryWrapper<Announcement> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Announcement::getPlay,1);
        if (this.getOne(queryWrapper)!=null) {
            throw new ServiceException("有公告正在播放");
        }
        LambdaUpdateWrapper<Announcement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Announcement::getId,id)
                .set(Announcement::getPlay,1);
        return this.update(updateWrapper);
    }
}
