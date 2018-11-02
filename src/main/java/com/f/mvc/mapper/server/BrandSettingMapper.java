package com.f.mvc.mapper.server;

import com.f.mvc.entity.server.BrandSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 上午11:02
 */
@Repository
public interface BrandSettingMapper {

    @Select("SELECT * FROM `brand_setting` WHERE `type`=#{type}")
    List<BrandSetting> findBrandSettingByType(@Param(value = "type") Integer type);

    @Select("SELECT COUNT(1) FROM `brand_setting` WHERE `brand`=#{brand} AND `type`=#{type}")
    int countByBrandAndType(@Param(value = "brand") String brand, @Param(value = "type") Integer type);

    @Insert("INSERT INTO `brand_setting` (`brand`,`name`,`type`) VALUES (#{brand},#{name},#{type})")
    @Options(useGeneratedKeys = true)
    int addNewBrandSetting(BrandSetting brandSetting);

    @Select("SELECT * FROM `brand_setting` WHERE `id`=#{id}")
    BrandSetting findBrandById(@Param("id") Integer id);

    @Select("SELECT * FROM `brand_setting` WHERE `brand`=#{brand} AND `type`=#{type} limit 1")
    BrandSetting findBrandByBrandAndType(@Param("brand") String brand, @Param("type") String type);
}
