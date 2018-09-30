package com.f.mvc.dao.auth;

import com.f.mvc.entity.User;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-24 下午2:17
 **/
public interface UserServiceDao {
    int modifyUser(User user);
    int addUser(User user);
}
