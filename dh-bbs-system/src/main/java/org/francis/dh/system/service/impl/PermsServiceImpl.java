package org.francis.dh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.francis.dh.common.core.entity.Perms;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.system.mapper.PermsMapper;
import org.francis.dh.system.service.PermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@Service
public class PermsServiceImpl extends ServiceImpl<PermsMapper, Perms> implements PermsService {

    @Autowired
    private PermsMapper permsMapper;


    @Override
    public List<Perms> getPermsByUid(Long userId) {
        return permsMapper.getPermsByUid(userId);
    }

    @Override
    public Set<String> getPermissions(User user) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        List<Perms> permsList = getPermsByUid(user.getId());
        permsList.forEach(perms1 -> perms.add(perms1.getPermsName()));
        return perms;
    }
}