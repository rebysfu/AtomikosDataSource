package com.f.mvc.dao.auth;

import com.f.constants.DataSourceKey;
import com.f.datasource.annotations.DataSource;
import com.f.mvc.entity.User;
import com.f.mvc.mapper.auth.UserMapper;
import com.f.mvc.mapper.auth.UserRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-24 下午2:18
 **/
@Repository
public class UserServiceDaoImpl implements UserServiceDao {
    @Resource
    private UserMapper userMapper;

    @DataSource(DataSourceKey.MASTER)
    @Override
    public int modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
