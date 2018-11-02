package com.f.mvc.service.auth;

import com.f.mvc.dao.auth.MenuDao;
import com.f.mvc.dao.auth.RoleMenuDao;
import com.f.mvc.entity.auth.Menu;
import com.f.mvc.entity.auth.RoleMenu;
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
    private MenuDao menuDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public int addNewMenu(Menu menu) {
        int row = menuDao.addNewMenu(menu);
        if (row > 0) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu.getId());
            roleMenu.setRoleId(1L);
            roleMenu.setCreateTime(new Date());
            roleMenu.setCreateUserId(1L);
            row = roleMenuDao.addNewRow(roleMenu);
        }
        return row;
    }

    @Override
    @Cacheable(value = "menuCache", key = "#p0", unless = "#result==null")
    public Menu findMenuById(Long id) {
        return menuDao.findMenuById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })
    @Transactional(rollbackFor = Exception.class)
    public int deleteMenuById(Menu menu) {
        roleMenuDao.deleteRoleMenuByMenuId(menu.getId());
        return menuDao.deleteMenuById(menu);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "menuCache", key = "#p0.id"),
            @CacheEvict(value = "roleMenuCache", allEntries = true),
            @CacheEvict(value = "menuAllCache", allEntries = true)
    })
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    @Cacheable(value = "menuAllCache", unless = "#result.isEmpty()")
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }
}
