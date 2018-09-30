package com.f.mvc.dao.info;

import com.f.constants.DataSourceKey;
import com.f.datasource.annotations.DataSource;
import com.f.mvc.entity.UserRole;
import com.f.mvc.mapper.info.InfoUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-24 下午2:18
 **/
@Repository
public class UserRoleServiceDaoImpl implements UserRoleServiceDao {
    @Resource
    private InfoUserRoleMapper infoUserRoleMapper;

    @DataSource(DataSourceKey.FISH2_INFO)
    @Override
    public int deleteUserByUserId(UserRole user) {
        return infoUserRoleMapper.deleteUserByUserId(user.getUserId());
    }
    @DataSource(DataSourceKey.FISH2_INFO)
    @Override
    public int addUserRole(UserRole userRole) {
        return infoUserRoleMapper.addUserRole(userRole);
    }
}
