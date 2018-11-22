package com.f.mvc.service.auth;

import com.f.mvc.entity.auth.User;
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
}
