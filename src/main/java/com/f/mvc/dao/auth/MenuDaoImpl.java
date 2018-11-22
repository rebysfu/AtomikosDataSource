package com.f.mvc.dao.auth;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.auth.Menu;
import com.f.mvc.mapper.auth.MenuMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/6
 * Time: 上午11:46
 */
@Repository
@DataSource(DataSourceKey.AUTH)
public class MenuDaoImpl implements MenuDao {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public int addNewMenu(Menu menu) {
        return menuMapper.addNewMenu(menu);
    }

    @Override
    public Menu findMenuById(Long id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public int deleteMenuById(Menu menu) {
        return menuMapper.deleteMenuById(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

}
