package com.f.mvc.dao.server;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.mapper.server.TunnelPayRelationMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午4:42
 **/
@DataSource(DataSourceKey.FISH2_SERVER)
@Repository
public class TunnelPayRelationDaoImpl implements TunnelPayRelationDao {
    @Resource
    private TunnelPayRelationMapper tunnelPayRelationMapper;


    @Override
    public int addNewTunnelPayRelation(TunnelPayRelation tunnelPayRelation) {
        return tunnelPayRelationMapper.addNewTunnelPayRelation(tunnelPayRelation);
    }

    @Override
    public int turnOperation(String tunnelPayId, boolean tag) {
        return tunnelPayRelationMapper.turnOperation(tunnelPayId, tag);
    }

    @Override
    public TunnelPayRelation findByTunnelPayId(String tunnelPayId) {
        return tunnelPayRelationMapper.findByTunnelPayId(tunnelPayId);
    }

    @Override
    public TunnelPayRelation findByPayTypeAndTunnelId(Integer payType, String tunnelId) {
        return tunnelPayRelationMapper.findByPayTypeAndTunnelId(payType, tunnelId);
    }

    @Override
    public List<TunnelPayRelation> findByPayType(Integer payType) {
        return tunnelPayRelationMapper.findByPayType(payType);
    }

    @Override
    public List<TunnelPayRelation> findAll() {
        return tunnelPayRelationMapper.findAll();
    }

    @Override
    public int update(TunnelPayRelation tunnelPayRelation) {
        return tunnelPayRelationMapper.update(tunnelPayRelation);
    }

    @Override
    public List<TunnelPayRelation> findByTunnelId(String tunnelId) {
        return tunnelPayRelationMapper.findByTunnelId(tunnelId);
    }

}
