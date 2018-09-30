package com.f.mvc.service;

import com.f.constants.DataSourceKey;
import com.f.datasource.annotations.DataSource;
import com.f.mvc.dao.auth.UserServiceDao;
import com.f.mvc.dao.info.UserRoleServiceDao;
import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.f.mvc.mapper.auth.UserMapper;
import com.f.mvc.mapper.auth.UserRoleMapper;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午12:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserRoleServiceDao userRoleServiceDao;
    @Resource
    private UserServiceDao userServiceDao;

    @Override
    public User findUserByAccount(final String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    @Cacheable(value = "userCache", key = "#p0", unless = "#result==null")
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "userCache", allEntries = true)
    public int addUser(User user, long[] roleIds) {
        int row = userMapper.addUser(user);
        if (row > 0) {
            for (long roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setCreateTime(user.getCreateTime());
                userRole.setUserId(user.getId());
                userRole.setSysRoleId(roleId);
                row = userRoleMapper.addUserRole(userRole);
                if (row < 1) throw new RuntimeException("Insert into error");
            }
        }
        return row;
    }

    @Transactional
    @Override
    @CacheEvict(value = "userCache", key = "#p0.id")
    public int modifyUser(User user) {
        return userMapper.modifyUser(user);
    }


    @Override
    public List<User> findUserByParam(String keyword, Page<User> page) {
        return userMapper.findUserByParam(keyword, page);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", key = "#p0.id"),
            @CacheEvict(value = "userRoleCache", key = "#p0.id"),
            @CacheEvict(value = "userPermissionCache", key = "#p0.id")
    })
    @Transactional
    public int removeUser(User user) {
        userMapper.deleteUser(user);
        userRoleMapper.deleteUserByUserId(user.getId());
        return 1;
    }

    @Override
    public List<User> findUserLikeParam(String param) {
        return userMapper.findUserLikeParam(param);
    }

    @Transactional
    @Override
    public int testTraction(User user,long[] roleIds) {
        int row=userServiceDao.addUser(user);
            if (row > 0) {
                for (long roleId : roleIds) {
                    UserRole userRole = new UserRole();
                    userRole.setCreateTime(user.getCreateTime());
                    userRole.setUserId(user.getId());
                    userRole.setSysRoleId(roleId);
                    row = userRoleServiceDao.addUserRole(userRole);
                    if (row < 1) {
                        throw new RuntimeException("Insert into error for UserRole");
                    }
                }
            }else {
                throw new RuntimeException("Insert into error for User");
            }

        return 0;
    }


}
