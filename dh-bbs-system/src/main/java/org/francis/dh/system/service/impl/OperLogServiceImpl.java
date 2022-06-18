package org.francis.dh.system.service.impl;

import org.francis.dh.system.entity.OperLog;
import org.francis.dh.system.mapper.OperLogMapper;
import org.francis.dh.system.service.OperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements OperLogService {

}
