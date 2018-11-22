package com.f.mvc.service.server;

import com.f.mvc.dao.server.BrandTunnelRelationDao;
import com.f.mvc.entity.server.BrandTunnelRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:31
 */
@Service
public class BrandTunnelRelationServiceImpl implements BrandTunnelRelationService {

    @Autowired
    private BrandTunnelRelationDao brandTunnelRelationDao;


    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelRelationTunnelPayIdCache", key = "#p0.tunnelPayId"),
            @CacheEvict(value = "tunnelRelationBrandCache", key = "#p0.brand"),
            @CacheEvict(value = "tunnelRelationBrandTunnelPayIdCache", key = "T(String).valueOf(#p0.brand).concat('_').concat(T(String).valueOf(#p0.tunnelPayId))"),
    })
    public int addNewBrandTunnelRelation(BrandTunnelRelation brandTunnelRelation) {
        return brandTunnelRelationDao.addNewBrandTunnelRelation(brandTunnelRelation);
    }

    @Override
    @Cacheable(value = "tunnelRelationTunnelPayIdCache", key = "#p0")
    public List<BrandTunnelRelation> findByTunnelPayId(String tunnelPayId) {
        return brandTunnelRelationDao.findByTunnelPayId(tunnelPayId);
    }

    @Override
    @Cacheable(value = "tunnelRelationBrandCache", key = "#p0", unless = "#result.isEmpty()")
    public List<BrandTunnelRelation> findByBrand(String brand) {
        return brandTunnelRelationDao.findByBrand(brand);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelRelationTunnelPayIdCache", key = "#p0.tunnelPayId"),
            @CacheEvict(value = "tunnelRelationBrandCache", key = "#p0.brand"),
            @CacheEvict(value = "tunnelRelationBrandTunnelPayIdCache", key = "T(String).valueOf(#p0.brand).concat('_').concat(T(String).valueOf(#p0.tunnelPayId))"),
    })
    public int updateWeightByTunnelPayId(BrandTunnelRelation brandTunnelRelation) {
        return brandTunnelRelationDao.updateWeightByTunnelPayId(brandTunnelRelation);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "tunnelRelationTunnelPayIdCache", allEntries = true),
            @CacheEvict(value = "tunnelRelationBrandCache", key = "#p1"),
            @CacheEvict(value = "tunnelRelationBrandTunnelPayIdCache", allEntries = true),
    })
    public int turnOperation(List<String> tunnelPayIds, String brand, Integer state) {
        return brandTunnelRelationDao.turnOperation(tunnelPayIds, brand, state);
    }

    @Override
    @Cacheable(value = "tunnelRelationBrandTunnelPayIdCache", key = "T(String).valueOf(#p0).concat('_').concat(T(String).valueOf(#p1))")
    public BrandTunnelRelation findByBrandAndTunnelPayId(String brand, String tunnelPayId) {
        return brandTunnelRelationDao.findByBrandAndTunnelPayId(brand, tunnelPayId);
    }

    @Override
    @CacheEvict(value = {"tunnelRelationTunnelPayIdCache", "tunnelRelationBrandCache", "tunnelRelationBrandTunnelPayIdCache"}, allEntries = true)
    public int batchAddBrandTunnelRelation(List<BrandTunnelRelation> list) {
        return brandTunnelRelationDao.batchAddBrandTunnelRelation(list);
    }
}
