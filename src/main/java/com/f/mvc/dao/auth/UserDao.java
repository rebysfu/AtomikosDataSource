package com.f.mvc.dao.auth;

import com.f.mvc.entity.User;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-24 下午2:17
 **/
public interface UserDao {

    int addUser(User user);

    User findUserByAccount(String account);

    User findUserById(Long id);

    int modifyUser(User user);

    List<User> findUserByParam(String keyword, Page<User> page);

    int removeUser(User user);

    List<User> findUserLikeParam(String param);
}
