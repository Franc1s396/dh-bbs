package org.francis.dh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.entity.Perms;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.common.utils.StringUtils;
import org.francis.dh.system.mapper.PermsMapper;
import org.francis.dh.system.service.PermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@Service("Perms")
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

    @Override
    public Boolean hasPerm(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUser user = SecurityUtils.getLoginUser();
        if (StringUtils.isNull(user) || CollectionUtils.isEmpty(user.getPermissions())) {
            return false;
        }
        return hasPermissions(user.getPermissions(), permission);
    }

    @Override
    public Boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains("*:*:*") || permissions.contains(StringUtils.trim(permission));
    }
}