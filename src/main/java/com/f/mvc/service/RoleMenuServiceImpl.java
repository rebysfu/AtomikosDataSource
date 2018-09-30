package com.f.mvc.service;

import com.f.mvc.entity.RoleMenu;
import com.f.mvc.mapper.auth.RoleMenuMapper;
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
    private RoleMenuMapper roleMenuMapper;

    @Override
    @CacheEvict(value = "roleMenuCache", key = "#p0.roleId")
    public int addNewRow(RoleMenu roleMenu) {
        return roleMenuMapper.addNewRow(roleMenu);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "roleMenuCache", key = "#p0.roleId"),
            @CacheEvict(value = "roleMenuIdCache", key = "#p0.id")
    })

    public int deleteRoleMenuById(RoleMenu roleMenu) {
        return roleMenuMapper.deleteRoleMenuById(roleMenu);
    }

    @Override
    @Cacheable(value = "roleMenuCache", key = "#p0", unless = "#result.isEmpty()")
    public List<RoleMenu> findRoleMenuByRoleId(Long roleId) {
        return roleMenuMapper.findRoleMenuByRoleId(roleId);
    }

    @Override
    @Cacheable(value = "roleMenuIdCache", key = "#p0", unless = "#result==null")
    public RoleMenu findRoleMenuById(Long id) {
        return roleMenuMapper.findRoleMenuById(id);
    }

    @Override
    public List<RoleMenu> findAllRoleMenu() {
        return roleMenuMapper.findAllRoleMenu();
    }
}
