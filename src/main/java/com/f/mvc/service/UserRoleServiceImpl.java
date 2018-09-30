package com.f.mvc.service;

import com.f.mvc.entity.UserRole;
import com.f.mvc.mapper.auth.UserRoleMapper;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:09
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Cacheable(value = "userRoleCache", key = "#p0")
    public List<UserRole> findByUserId(Long userId) {
        return userRoleMapper.findByUserId(userId);
    }

    @Override
    @CacheEvict(value = "userRoleCache", key = "#p0.userId")
    public int addUserRole(UserRole userRole) {
        return userRoleMapper.addUserRole(userRole);
    }


    @Override
    @CacheEvict(value = "userRoleCache", key = "#p0.userId")
    public int deleteUserRole(UserRole userRole) {
        return userRoleMapper.deleteUserRole(userRole);
    }

    @Override
    @CacheEvict(value = "userRoleCache", key = "#p0")
    @Transactional(rollbackFor = Exception.class)
    public int userRoleBatch(long userId, long[] newly, long[] waste) throws Exception {
        if (newly.length > 0) {
            for (long n : newly) {
                UserRole userRole = new UserRole();
                userRole.setSysRoleId(n);
                userRole.setUserId(userId);
                userRole.setCreateTime(new Date());
                int row = userRoleMapper.addUserRole(userRole);
                if (row < 1) throw new Exception("Add new user role");
            }
        }
        if (waste.length > 0) {
            List<String> list = Lists.newArrayList();
            for (long w : waste) list.add(String.valueOf(w));
            int row = userRoleMapper.deleteUserRoleByParam(userId, Strings.collectionToDelimitedString(list, ","));
            if (row < 1) throw new Exception("Remove old user role");
        }
        return 1;
    }
}
