package com.f.mvc.dao.server;

import com.f.mvc.entity.server.BrandSetting;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 上午11:05
 */
public interface BrandSettingDao {

    List<BrandSetting> findBrandSettingByType(final Integer type);

    int countByBrandAndType(final String brand, final Integer type);

    int addNewBrandSetting(BrandSetting brandSetting);

    BrandSetting findBrandById(final Integer id);

    BrandSetting findBrandByBrandAndType(final String brand, final String type);
}
