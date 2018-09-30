package com.f.mvc.service;


import com.f.mvc.entity.UserRole;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:09
 */
public interface UserRoleService {
    List<UserRole> findByUserId(Long userId);

    int addUserRole(UserRole userRole);

    int deleteUserRole(UserRole userRole);

    int userRoleBatch(long userId, long[] newly, long[] waste) throws Exception;

}
