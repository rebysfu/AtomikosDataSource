package com.f.mvc.service.auth;

import com.f.mvc.dao.auth.SysRoleDao;
import com.f.mvc.entity.auth.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:08
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    @Cacheable(value = "sysRoleAllCache", unless = "#result.isEmpty()")
    public List<SysRole> findAll() {
        return sysRoleDao.findAllSysRole();
    }

    @Override
    @Cacheable(value = "sysRoleCache", key = "#p0", unless = "#result==null")
    public SysRole findSysRoleById(Long id) {
        return sysRoleDao.findSysRoleById(id);
    }

    /**
     * @param role
     * @return
     */
    @Override
    @Cacheable(value = "sysRoleRoleCache", key = "#p0", unless = "#result==null")
    public SysRole findSysRoleByRole(String role) {
        return sysRoleDao.findSysRoleByRole(role);
    }

    /**
     * @param sysRole
     * @return
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = "sysRoleAllCache", allEntries = true),
            @CacheEvict(value = "sysRoleCache", allEntries = true),
            @CacheEvict(value = "sysRoleRoleCache", allEntries = true)
    })
    public int addSysRole(SysRole sysRole) {
        return sysRoleDao.addSysRole(sysRole);
    }
}
