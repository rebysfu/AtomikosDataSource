package com.f.mvc.service.server;

import com.f.mvc.dao.server.TunnelSettingDao;
import com.f.mvc.entity.server.TunnelSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午5:13
 **/
@Service
public class TunnelSettingServiceImpl implements TunnelSettingService {

    @Autowired
    private TunnelSettingDao tunnelSettingDao;


    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelSettingTunnelTypeCache", key = "#p0.tunnelType"),
            @CacheEvict(value = "tunnelSettingCache", allEntries = true),
            @CacheEvict(value = "tunnelSettingTunnelIdCache", key = "#p0.tunnelId")
    })
    public int addNewTunnelSetting(TunnelSetting tunnelSetting) {
        return tunnelSettingDao.addNewTunnelSetting(tunnelSetting);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelSettingTunnelTypeCache", key = "#p0.tunnelType"),
            @CacheEvict(value = "tunnelSettingCache", allEntries = true),
            @CacheEvict(value = "tunnelSettingTunnelIdCache", key = "#p0.tunnelId")
    })
    public int turnOperation(TunnelSetting tunnelSetting) {
        return tunnelSettingDao.turnOperation(tunnelSetting.getTunnelId(), tunnelSetting.getTurnOn());
    }

    @Override
    @Cacheable(value = "tunnelSettingTunnelTypeCache", key = "#p0", unless = "#result.isEmpty()")
    public List<TunnelSetting> findByTunnelType(Integer tunnelType) {
        return tunnelSettingDao.findByTunnelType(tunnelType);
    }

    @Override
    @Cacheable(value = "tunnelSettingCache", unless = "#result.isEmpty()")
    public List<TunnelSetting> findAll() {
        return tunnelSettingDao.findAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelSettingTunnelTypeCache", key = "#p0.tunnelType"),
            @CacheEvict(value = "tunnelSettingCache", allEntries = true),
            @CacheEvict(value = "tunnelSettingTunnelIdCache", key = "#p0.tunnelId")
    })
    public int updateTunnelSetting(TunnelSetting tunnelSetting) {
        return tunnelSettingDao.updateTunnelSetting(tunnelSetting);
    }

    @Override
    @Cacheable(value = "tunnelSettingTunnelIdCache", key = "#p0", unless = "#result==null")
    public TunnelSetting findByTunnelId(String tunnelId) {
        return tunnelSettingDao.findByTunnelId(tunnelId);
    }
}
