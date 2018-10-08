package com.f.mvc.service;

import com.f.mvc.dao.auth.UserRoleDao;
import com.f.mvc.entity.UserRole;
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
    private UserRoleDao userRoleDao;

    @Override
    @Cacheable(value = "userRoleCache", key = "#p0")
    public List<UserRole> findByUserId(Long userId) {
        return userRoleDao.findByUserId(userId);
    }

    @Override
    @CacheEvict(value = "userRoleCache", key = "#p0.userId")
    public int addUserRole(UserRole userRole) {
        return userRoleDao.addUserRole(userRole);
    }


    @Override
    @CacheEvict(value = "userRoleCache", key = "#p0.userId")
    public int deleteUserRole(UserRole userRole) {
        return userRoleDao.deleteUserRole(userRole);
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
                int row = userRoleDao.addUserRole(userRole);
                if (row < 1) throw new Exception("Add new user role");
            }
        }
        if (waste.length > 0) {
            List<String> list = Lists.newArrayList();
            for (long w : waste) list.add(String.valueOf(w));
            int row = userRoleDao.deleteUserRoleByParam(userId, Strings.collectionToDelimitedString(list, ","));
            if (row < 1) throw new Exception("Remove old user role");
        }
        return 1;
    }
}
