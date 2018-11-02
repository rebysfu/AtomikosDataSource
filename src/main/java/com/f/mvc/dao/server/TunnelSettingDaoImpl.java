package com.f.mvc.dao.server;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.server.TunnelSetting;
import com.f.mvc.mapper.server.TunnelSettingMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午5:05
 **/
@DataSource(DataSourceKey.FISH2_SERVER)
@Repository
public class TunnelSettingDaoImpl implements TunnelSettingDao {

    @Resource
    private TunnelSettingMapper tunnelSettingMapper;

    @Override
    public int addNewTunnelSetting(TunnelSetting tunnelSetting) {
        return tunnelSettingMapper.addNewTunnelSetting(tunnelSetting);
    }

    @Override
    public TunnelSetting findByTunnelId(String tunnelId) {
        return tunnelSettingMapper.findByTunnelId(tunnelId);
    }

    @Override
    public List<TunnelSetting> findByTunnelType(Integer tunnelType) {
        return tunnelSettingMapper.findByTunnelType(tunnelType);
    }

    @Override
    public List<TunnelSetting> findAll() {
        return tunnelSettingMapper.findAll();
    }

    @Override
    public int updateTunnelSetting(TunnelSetting tunnelSetting) {
        return tunnelSettingMapper.updateTunnelSetting(tunnelSetting);
    }

    @Override
    public int turnOperation(String tunnelId, boolean tag) {
        return tunnelSettingMapper.turnOperation(tunnelId, tag);
    }
}
