package com.f.mvc.service.auth;

import com.f.mvc.entity.auth.SysRole;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:08
 */
public interface SysRoleService {
    List<SysRole> findAll();

    SysRole findSysRoleById(Long id);

    SysRole findSysRoleByRole(String role);

    int addSysRole(SysRole role);
}
