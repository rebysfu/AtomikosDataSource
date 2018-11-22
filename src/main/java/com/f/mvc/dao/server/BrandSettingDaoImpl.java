package com.f.mvc.dao.server;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.server.BrandSetting;
import com.f.mvc.mapper.server.BrandSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 上午11:05
 */
@Repository
@DataSource(DataSourceKey.FISH2_SERVER)
public class BrandSettingDaoImpl implements BrandSettingDao {

    @Autowired
    private BrandSettingMapper brandSettingMapper;

    @Override
    public List<BrandSetting> findBrandSettingByType(Integer type) {
        return brandSettingMapper.findBrandSettingByType(type);
    }

    @Override
    public int countByBrandAndType(String brand, Integer type) {
        return brandSettingMapper.countByBrandAndType(brand, type);
    }

    @Override
    public int addNewBrandSetting(BrandSetting brandSetting) {
        return brandSettingMapper.addNewBrandSetting(brandSetting);
    }

    @Override
    public BrandSetting findBrandById(Integer id) {
        return brandSettingMapper.findBrandById(id);
    }

    @Override
    public BrandSetting findBrandByBrandAndType(String brand, String type) {
        return brandSettingMapper.findBrandByBrandAndType(brand, type);
    }
}
