package com.f.mvc.service.server;

import com.f.mvc.dao.server.TunnelPayRelationDao;
import com.f.mvc.entity.server.TunnelPayRelation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午4:43
 **/
@Service
public class TunnelPayRelationServiceImpl implements TunnelPayRelationService {
    @Resource
    private TunnelPayRelationDao tunnelPayRelationDao;


    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelPayRelationTunnelPayIdCache", key = "#p0.tunnelPayId"),
            @CacheEvict(value = "tunnelPayRelationPayTypeTunnelPayIdCache", key = "T(String).valueOf(#p0.payType).concat('_').concat(T(String).valueOf(#p0.tunnelId))"),
            @CacheEvict(value = "tunnelPayRelationPayTypeCache", key = "T(String).valueOf(#p0.payType)"),
            @CacheEvict(value = "tunnelPayRelationCache", allEntries = true),
            @CacheEvict(value = "tunnelPayRelationTunnelIdCache", key = "#p0.tunnelId")
    })
    public int addNewTunnelPayRelation(TunnelPayRelation tunnelPayRelation) {
        return tunnelPayRelationDao.addNewTunnelPayRelation(tunnelPayRelation);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelPayRelationTunnelPayIdCache", key = "#p0.tunnelPayId"),
            @CacheEvict(value = "tunnelPayRelationPayTypeTunnelPayIdCache", key = "T(String).valueOf(#p0.payType).concat('_').concat(T(String).valueOf(#p0.tunnelId))"),
            @CacheEvict(value = "tunnelPayRelationPayTypeCache", key = "T(String).valueOf(#p0.payType)"),
            @CacheEvict(value = "tunnelPayRelationCache", allEntries = true),
            @CacheEvict(value = "tunnelPayRelationTunnelIdCache", key = "#p0.tunnelId")
    })
    public int turnOperation(TunnelPayRelation tunnelPayRelation) {
        return tunnelPayRelationDao.turnOperation(tunnelPayRelation.getTunnelPayId(), tunnelPayRelation.getTurnOn());
    }

    @Override
    @Cacheable(value = "tunnelPayRelationTunnelPayIdCache", key = "#p0", unless = "#result==null")
    public TunnelPayRelation findByTunnelPayId(String tunnelPayId) {
        return tunnelPayRelationDao.findByTunnelPayId(tunnelPayId);
    }

    @Override
    @Cacheable(value = "tunnelPayRelationPayTypeTunnelPayIdCache", key = "T(String).valueOf(#p0).concat('_').concat(T(String).valueOf(#p1))", unless = "#result==null")
    public TunnelPayRelation findByPayTypeAndTunnelId(Integer payType, String tunnelId) {
        return tunnelPayRelationDao.findByPayTypeAndTunnelId(payType, tunnelId);
    }

    @Override
    @Cacheable(value = "tunnelPayRelationPayTypeCache", key = "T(String).valueOf(#p0)", unless = "#result.isEmpty()")
    public List<TunnelPayRelation> findByPayType(Integer payType) {
        return tunnelPayRelationDao.findByPayType(payType);
    }

    @Override
    @Cacheable(value = "tunnelPayRelationCache", unless = "#result.isEmpty()")
    public List<TunnelPayRelation> findAll() {
        return tunnelPayRelationDao.findAll();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelPayRelationTunnelPayIdCache", key = "#p0.tunnelPayId"),
            @CacheEvict(value = "tunnelPayRelationPayTypeTunnelPayIdCache", key = "T(String).valueOf(#p0.payType).concat('_').concat(T(String).valueOf(#p0.tunnelId))"),
            @CacheEvict(value = "tunnelPayRelationPayTypeCache", key = "T(String).valueOf(#p0.payType)"),
            @CacheEvict(value = "tunnelPayRelationCache", allEntries = true),
            @CacheEvict(value = "tunnelPayRelationTunnelIdCache", key = "#p0.tunnelId")
    })
    public int update(TunnelPayRelation tunnelPayRelation) {
        return tunnelPayRelationDao.update(tunnelPayRelation);
    }

    @Override
    @Cacheable(value = "tunnelPayRelationTunnelIdCache", key = "#p0", unless = "#result.isEmpty()")
    public List<TunnelPayRelation> findByTunnelId(String tunnelId) {
        return tunnelPayRelationDao.findByTunnelId(tunnelId);
    }
}
