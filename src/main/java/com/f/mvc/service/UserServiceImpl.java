package com.f.mvc.service;

import com.f.mvc.dao.auth.UserDao;
import com.f.mvc.dao.auth.UserRoleDao;
import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.github.pagehelper.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午12:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public User findUserByAccount(final String account) {
        return userDao.findUserByAccount(account);
    }

    @Override
    @Cacheable(value = "userCache", key = "#p0", unless = "#result==null")
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "userCache", allEntries = true)
    public int addUser(User user, long[] roleIds) {
        int row = userDao.addUser(user);
        if (row > 0) {
            for (long roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setCreateTime(user.getCreateTime());
                userRole.setUserId(user.getId());
                userRole.setSysRoleId(roleId);
                row = userRoleDao.addUserRole(userRole);
                if (row < 1) throw new RuntimeException("Insert into error");
            }
        }
        return row;
    }

    @Transactional
    @Override
    @CacheEvict(value = "userCache", key = "#p0.id")
    public int modifyUser(User user) {
        return userDao.modifyUser(user);
    }


    @Override
    public List<User> findUserByParam(String keyword, Page<User> page) {
        return userDao.findUserByParam(keyword, page);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "#p0.id"),
            @CacheEvict(value = "userRoleCache", key = "#p0.id"),
            @CacheEvict(value = "userPermissionCache", key = "#p0.id")
    })
    @Transactional
    public int removeUser(User user) {
        userDao.removeUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getId());
        return 1;
    }

    @Override
    public List<User> findUserLikeParam(String param) {
        return userDao.findUserLikeParam(param);
    }

}
