package com.f.mvc.service.server;

import com.f.mvc.dao.server.BrandSettingDao;
import com.f.mvc.entity.server.BrandSetting;
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
 * Time: 上午11:09
 */
@Service
public class BrandSettingServiceImpl implements BrandSettingService {
    @Autowired
    private BrandSettingDao brandSettingDao;


    @Override
    @Cacheable(value = "brandSettingTypeCache", key = "#p0", unless = "#result.isEmpty()")
    public List<BrandSetting> findBrandSettingByType(Integer type) {
        return brandSettingDao.findBrandSettingByType(type);
    }

    @Override
    @Cacheable(value = "brandSettingCountCache", key = "T(String).valueOf(#p0).concat('_').concat(T(String).valueOf(#p1))")
    public int countByBrandAndType(String brand, Integer type) {
        return brandSettingDao.countByBrandAndType(brand, type);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "brandSettingTypeCache", key = "#p0.type"),
                    @CacheEvict(value = "brandSettingCountCache", key = "T(String).valueOf(#p0.brand).concat('_').concat(T(String).valueOf(#p0.type))"),
                    @CacheEvict(value = "brandSettingBrandTypeCache", key = "T(String).valueOf(#p0.brand).concat('_').concat(T(String).valueOf(#p0.type))")
            }
    )
    public int addNewBrandSetting(BrandSetting brandSetting) {
        return brandSettingDao.addNewBrandSetting(brandSetting);
    }


    @Override
    @Cacheable(value = "brandSettingBrandTypeCache", key = "T(String).valueOf(#p0).concat('_').concat(T(String).valueOf(#p1))", unless = "#result==null")
    public BrandSetting findBrandByBrandAndType(final String brand, final String type) {
        return brandSettingDao.findBrandByBrandAndType(brand, type);
    }
}
