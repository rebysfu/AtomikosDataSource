package com.f.mvc.service;

import com.f.mvc.dao.auth.RoleMenuDao;
import com.f.mvc.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:45
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    @CacheEvict(value = "roleMenuCache", key = "#p0.roleId")
    public int addNewRow(RoleMenu roleMenu) {
        return roleMenuDao.addNewRow(roleMenu);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "roleMenuCache", key = "#p0.roleId"),
            @CacheEvict(value = "roleMenuIdCache", key = "#p0.id")
    })

    public int deleteRoleMenuById(RoleMenu roleMenu) {
        return roleMenuDao.deleteRoleMenuById(roleMenu);
    }

    @Override
    @Cacheable(value = "roleMenuCache", key = "#p0", unless = "#result.isEmpty()")
    public List<RoleMenu> findRoleMenuByRoleId(Long roleId) {
        return roleMenuDao.findRoleMenuByRoleId(roleId);
    }

    @Override
    @Cacheable(value = "roleMenuIdCache", key = "#p0", unless = "#result==null")
    public RoleMenu findRoleMenuById(Long id) {
        return roleMenuDao.findRoleMenuById(id);
    }

    @Override
    public List<RoleMenu> findAllRoleMenu() {
        return roleMenuDao.findAllRoleMenu();
    }
}
