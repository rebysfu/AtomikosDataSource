package com.f.mvc.service;

import com.f.mvc.entity.Menu;
import com.f.mvc.entity.RoleMenu;
import com.f.mvc.mapper.auth.MenuMapper;
import com.f.mvc.mapper.auth.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:31
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })
    @Transactional
    public int addNewMenu(Menu menu) {
        int row = menuMapper.addNewMenu(menu);
        if (row > 0) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu.getId());
            roleMenu.setRoleId(1L);
            roleMenu.setCreateTime(new Date());
            roleMenu.setCreateUserId(1L);
            row = roleMenuMapper.addNewRow(roleMenu);
        }
        return row;
    }

    @Override
    @Cacheable(value = "menuCache", key = "#p0", unless = "#result==null")
    public Menu findMenuById(Long id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })

    public int deleteMenuById(Menu menu) {
        roleMenuMapper.deleteRoleMenuByMenuId(menu.getId());
        return menuMapper.deleteMenuById(menu);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    @Cacheable(value = "menuAllCache", unless = "#result.isEmpty()")
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }
}
