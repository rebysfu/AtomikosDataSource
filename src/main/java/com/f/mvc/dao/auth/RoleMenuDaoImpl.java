package com.f.mvc.dao.auth;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.RoleMenu;
import com.f.mvc.mapper.auth.RoleMenuMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/6
 * Time: 上午11:49
 */
@Repository
@DataSource(DataSourceKey.AUTH)
public class RoleMenuDaoImpl implements RoleMenuDao {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int addNewRow(RoleMenu roleMenu) {
        return roleMenuMapper.addNewRow(roleMenu);
    }

    @Override
    public int deleteRoleMenuById(RoleMenu roleMenu) {
        return roleMenuMapper.deleteRoleMenuById(roleMenu);
    }

    @Override
    public int deleteRoleMenuByMenuId(Long menuId) {
        return roleMenuMapper.deleteRoleMenuByMenuId(menuId);
    }

    @Override
    public List<RoleMenu> findRoleMenuByRoleId(Long roleId) {
        return roleMenuMapper.findRoleMenuByRoleId(roleId);
    }

    @Override
    public RoleMenu findRoleMenuById(Long id) {
        return roleMenuMapper.findRoleMenuById(id);
    }

    @Override
    public List<RoleMenu> findAllRoleMenu() {
        return roleMenuMapper.findAllRoleMenu();
    }
}
