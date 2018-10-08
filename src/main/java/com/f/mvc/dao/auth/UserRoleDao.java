package com.f.mvc.dao.auth;


import com.f.mvc.entity.UserRole;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:09
 */
public interface UserRoleDao {
    List<UserRole> findByUserId(Long userId);

    int addUserRole(UserRole userRole);

    int deleteUserRole(UserRole userRole);

    int deleteUserRoleByParam(long userId, String param);

    int deleteUserRoleByUserId(Long userId);
}
