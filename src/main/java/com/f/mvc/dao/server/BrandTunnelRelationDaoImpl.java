package com.f.mvc.dao.server;

import com.f.datasource.annotations.DataSource;
import com.f.enums.DataSourceKey;
import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.mapper.server.BrandTunnelRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:30
 */
@Repository
@DataSource(DataSourceKey.FISH2_SERVER)
public class BrandTunnelRelationDaoImpl implements BrandTunnelRelationDao {

    @Autowired
    private BrandTunnelRelationMapper brandTunnelRelationMapper;

    @Override
    public int addNewBrandTunnelRelation(BrandTunnelRelation brandTunnelRelation) {
        return brandTunnelRelationMapper.addNewBrandTunnelRelation(brandTunnelRelation);
    }

    @Override
    public List<BrandTunnelRelation> findByTunnelPayId(String tunnelPayId) {
        return brandTunnelRelationMapper.findByTunnelPayId(tunnelPayId);
    }

    @Override
    public List<BrandTunnelRelation> findByBrand(String brand) {
        return brandTunnelRelationMapper.findByBrand(brand);
    }

    @Override
    public int updateWeightByTunnelPayId(BrandTunnelRelation brandTunnelRelation) {
        return brandTunnelRelationMapper.updateWeightByTunnelPayId(brandTunnelRelation);
    }

    @Override
    public int turnOperation(List<String> tunnelPayIds, String brand, Integer state) {
        return brandTunnelRelationMapper.turnOperation(tunnelPayIds, brand, state);
    }

    @Override
    public BrandTunnelRelation findByBrandAndTunnelPayId(String brand, String tunnelPayId) {
        return brandTunnelRelationMapper.findByBrandAndTunnelPayId(brand, tunnelPayId);
    }

    @Override
    public int batchAddBrandTunnelRelation(List<BrandTunnelRelation> list) {
        return brandTunnelRelationMapper.batchAddBrandTunnelRelation(list);
    }
}
