package com.f.mvc.dao.info;

import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-24 下午2:17
 **/
public interface UserRoleServiceDao {
    int deleteUserByUserId(UserRole user);
    int addUserRole(UserRole userRole);

}
