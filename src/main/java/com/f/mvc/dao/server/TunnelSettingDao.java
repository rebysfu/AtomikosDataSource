package com.f.mvc.dao.server;

import com.f.mvc.entity.server.TunnelSetting;

import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午5:05
 **/
public interface TunnelSettingDao {
    int addNewTunnelSetting(TunnelSetting tunnelSetting);

    int turnOperation(String tunnelId, boolean tag);

    TunnelSetting findByTunnelId(final String tunnelId);

    List<TunnelSetting> findByTunnelType(final Integer tunnelType);

    List<TunnelSetting> findAll();

    int updateTunnelSetting(TunnelSetting tunnelSetting);
}
