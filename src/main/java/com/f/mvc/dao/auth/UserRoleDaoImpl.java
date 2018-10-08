package com.f.mvc.dao.auth;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.UserRole;
import com.f.mvc.mapper.auth.UserRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/6
 * Time: 上午11:54
 */
@Repository
@DataSource(DataSourceKey.AUTH)
public class UserRoleDaoImpl implements UserRoleDao {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleMapper.findByUserId(userId);
    }

    @Override
    public int addUserRole(UserRole userRole) {
        return userRoleMapper.addUserRole(userRole);
    }

    @Override
    public int deleteUserRole(UserRole userRole) {
        return userRoleMapper.deleteUserRole(userRole);
    }

    @Override
    public int deleteUserRoleByParam(long userId, String param) {
        return userRoleMapper.deleteUserRoleByParam(userId, param);
    }

    @Override
    public int deleteUserRoleByUserId(Long userId) {
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }
}
