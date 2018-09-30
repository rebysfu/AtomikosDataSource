package com.f.mvc.service;

import com.f.mvc.entity.RoleMenu;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:45
 */
public interface RoleMenuService {

    int addNewRow(RoleMenu roleMenu);

    int deleteRoleMenuById(RoleMenu roleMenu);

    List<RoleMenu> findRoleMenuByRoleId(Long roleId);

    RoleMenu findRoleMenuById(Long id);

    List<RoleMenu> findAllRoleMenu();
}
