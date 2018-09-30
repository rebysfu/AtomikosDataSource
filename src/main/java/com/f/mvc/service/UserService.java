package com.f.mvc.service;

import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午12:13
 */
public interface UserService {

    User findUserByAccount(String account);

    User findUserById(Long id);

    int addUser(User user, long[] roleIds);

    int modifyUser(User user);

    List<User> findUserByParam(String keyword, Page<User> page);

    int removeUser(User user);

    List<User> findUserLikeParam(String param);

    int testTraction(User user, long[] roleIds);
}
