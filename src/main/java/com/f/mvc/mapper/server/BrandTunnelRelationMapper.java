package com.f.mvc.mapper.server;

import com.f.mvc.entity.server.BrandTunnelRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:02
 */
@Repository
public interface BrandTunnelRelationMapper {

    @Insert("INSERT INTO `brand_tunnel_relation` (`brand`, `tunnel_pay_id`, `weight`, `state`) VALUES (#{brand},#{tunnelPayId},#{weight},#{state})")
    @Options(useGeneratedKeys = true)
    int addNewBrandTunnelRelation(BrandTunnelRelation brandTunnelRelation);

    @Select("SELECT * FROM `brand_tunnel_relation` WHERE `tunnel_pay_id`=#{tunnelPayId}")
    List<BrandTunnelRelation> findByTunnelPayId(@Param(value = "tunnelPayId") String tunnelPayId);

    @Select("SELECT * FROM `brand_tunnel_relation` WHERE `brand`=#{brand}")
    List<BrandTunnelRelation> findByBrand(@Param(value = "brand") String brand);

    @Update("UPDATE `brand_tunnel_relation` SET `weight`=#{weight}  WHERE `tunnel_pay_id`=#{tunnelPayId}")
    int updateWeightByTunnelPayId(BrandTunnelRelation brandTunnelRelation);

    @Update("<script>" +
            "update `brand_tunnel_relation` SET `state`=#{state}  where brand=#{brand} and id in " +
            "<foreach item='item' index='index' collection='tunnelPayIds' open='(' separator=', ' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int turnOperation(@Param("tunnelPayIds") List<String> tunnelPayIds, @Param("brand") String brand, @Param("state") Integer state);

    @Select("SELECT * FROM `brand_tunnel_relation` WHERE `brand`=#{brand} AND `tunnel_pay_id`=#{tunnelPayId}")
    BrandTunnelRelation findByBrandAndTunnelPayId(@Param(value = "brand") String brand, @Param(value = "tunnelPayId") String tunnelPayId);

    @Insert("<script>" +
            "INSERT INTO `brand_tunnel_relation` (`brand`, `tunnel_pay_id`, `weight`, `state`) VALUES" +
            "<foreach item='item' index='index' collection='list' separator =','>" +
            "(#{item.brand},#{item.tunnelPayId},#{item.weight},#{item.state})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true)
    int batchAddBrandTunnelRelation(@Param("list") List<BrandTunnelRelation> list);
}
