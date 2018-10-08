package com.f.mvc.dao.auth;

import com.f.mvc.entity.Menu;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/6
 * Time: 上午11:45
 */
public interface MenuDao {
    int addNewMenu(Menu menu);

    Menu findMenuById(Long id);

    int deleteMenuById(Menu menu);

    int updateMenu(Menu menu);

    List<Menu> findAllMenu();

}
