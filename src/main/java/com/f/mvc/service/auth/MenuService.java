package com.f.mvc.service.auth;

import com.f.mvc.entity.auth.Menu;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:31
 */
public interface MenuService {

    int addNewMenu(Menu menu);

    Menu findMenuById(Long id);

    int deleteMenuById(Menu menu);

    int updateMenu(Menu menu);

    List<Menu> findAllMenu();
}
