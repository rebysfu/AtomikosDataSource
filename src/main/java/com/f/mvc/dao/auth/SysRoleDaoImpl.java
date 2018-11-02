package com.f.mvc.dao.auth;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.auth.SysRole;
import com.f.mvc.mapper.auth.SysRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/6
 * Time: 上午11:51
 */
@Repository
@DataSource(DataSourceKey.AUTH)
public class SysRoleDaoImpl implements SysRoleDao {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findAllSysRole() {
        return sysRoleMapper.findAllSysRole();
    }

    @Override
    public SysRole findSysRoleById(Long id) {
        return sysRoleMapper.findSysRoleById(id);
    }

    @Override
    public SysRole findSysRoleByRole(String role) {
        return sysRoleMapper.findSysRoleByRole(role);
    }

    @Override
    public int addSysRole(SysRole role) {
        return sysRoleMapper.addSysRole(role);
    }
}
