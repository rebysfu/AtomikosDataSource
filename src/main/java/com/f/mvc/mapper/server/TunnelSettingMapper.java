package com.f.mvc.mapper.server;

import com.f.mvc.entity.server.TunnelSetting;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/9
 * Time: 上午11:24
 */
@Repository
public interface TunnelSettingMapper {

    @Insert("INSERT INTO `tunnel_setting` (`tunnel_id`, `tunnel_type`, `tunnel_name`, `turn_on`, `area_black_list`, `ip_black_list`, `ch_black_list`, `mch_id`, `tunnel_key`, `pay_url`, `notify_url`, `extend_params`, `description`) " +
            "VALUES " +
            "(#{tunnelId},#{tunnelType},#{tunnelName},#{turnOn},#{areaBlackList},#{ipBlackList},#{chBlackList},#{mchId},#{tunnelKey},#{payUrl},#{notifyUrl},#{extendParams},#{description})")
    @Options(useGeneratedKeys = true)
    int addNewTunnelSetting(TunnelSetting tunnelSetting);

    @Update("update `tunnel_setting` set turn_on=#{tag} where tunnel_id=#{tunnelId} ")
    int turnOperation(@Param("tunnelId") String tunnelId, @Param("tag") boolean tag);

    @Select("SELECT * FROM `tunnel_setting` WHERE `tunnel_id`=#{tunnelId}")
    TunnelSetting findByTunnelId(@Param(value = "tunnelId") String tunnelId);


    @Select("SELECT * FROM `tunnel_setting` WHERE `tunnel_type`=#{tunnelType}")
    List<TunnelSetting> findByTunnelType(@Param("tunnelType") Integer tunnelType);

    @Select("SELECT * FROM `tunnel_setting`")
    List<TunnelSetting> findAll();


    @Update("UPDATE `tunnel_setting` SET `tunnel_type`=#{tunnelType},`tunnel_name`=#{tunnelName},`area_black_list`=#{areaBlackList},`ip_black_list`=#{ipBlackList},`ch_black_list`=#{chBlackList},`mch_id`=#{mchId},`tunnel_key`=#{tunnelKey},`pay_url`=#{payUrl},`notify_url`=#{notifyUrl},`extend_params`=#{extendParams},`description`=#{description} WHERE `id`=#{id}")
    int updateTunnelSetting(TunnelSetting tunnelSetting);


}
