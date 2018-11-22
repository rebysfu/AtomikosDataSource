package com.f.mvc.service.server;

import com.f.mvc.entity.server.TunnelSetting;

import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午5:12
 **/
public interface TunnelSettingService {
    int addNewTunnelSetting(TunnelSetting tunnelSetting);

    TunnelSetting findByTunnelId(final String tunnelId);

    int turnOperation(TunnelSetting tunnelSetting);

    List<TunnelSetting> findByTunnelType(final Integer tunnelType);

    List<TunnelSetting> findAll();

    int updateTunnelSetting(TunnelSetting tunnelSetting);
}
