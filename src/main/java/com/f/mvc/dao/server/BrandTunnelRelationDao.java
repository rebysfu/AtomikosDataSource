package com.f.mvc.dao.server;

import com.f.mvc.entity.server.BrandTunnelRelation;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:30
 */
public interface BrandTunnelRelationDao {


    int addNewBrandTunnelRelation(BrandTunnelRelation brandTunnelRelation);

    List<BrandTunnelRelation> findByTunnelPayId(final String tunnelPayId);

    List<BrandTunnelRelation> findByBrand(final String brand);

    int updateWeightByTunnelPayId(BrandTunnelRelation brandTunnelRelation);

    int turnOperation(List<String> tunnelPayIds, String brand, Integer state);

    BrandTunnelRelation findByBrandAndTunnelPayId(final String brand, final String tunnelPayId);

    int batchAddBrandTunnelRelation(List<BrandTunnelRelation> list);
}