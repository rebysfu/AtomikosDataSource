package com.f.mvc.mapper.server;

import com.f.mvc.entity.server.TunnelPayRelation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:03
 */
@Repository
public interface TunnelPayRelationMapper {

    @Insert("INSERT INTO `tunnel_pay_relation` (`tunnel_pay_id`, `pay_type`, `tunnel_id`, `turn_on`, `reduce_max`, `money_type`, `money_list`) VALUES (#{tunnelPayId},#{payType},#{tunnelId},#{turnOn},#{reduceMax},#{moneyType},#{moneyList})")
    @Options(useGeneratedKeys = true)
    int addNewTunnelPayRelation(TunnelPayRelation tunnelPayRelation);

    @Update("update `tunnel_pay_relation` set turn_on=#{tag} where tunnel_pay_id=#{tunnelPayId} ")
    int turnOperation(@Param("tunnelPayId") String tunnelPayId, @Param("tag") boolean tag);


    @Select("SELECT * FROM `tunnel_pay_relation` WHERE `tunnel_pay_id`=#{tunnelPayId}")
    TunnelPayRelation findByTunnelPayId(@Param(value = "tunnelPayId") String tunnelPayId);

    @Select("SELECT * FROM `tunnel_pay_relation` WHERE `pay_type`=#{payType} AND `tunnel_id`=#{tunnelId}")
    TunnelPayRelation findByPayTypeAndTunnelId(@Param("payType") Integer payType, @Param("tunnelId") String tunnelId);

    @Select("SELECT * FROM `tunnel_pay_relation` WHERE `pay_type`=#{payType}")
    List<TunnelPayRelation> findByPayType(@Param("payType") Integer payType);


    @Select("SELECT * FROM `tunnel_pay_relation`")
    List<TunnelPayRelation> findAll();

    @Update("update `tunnel_pay_relation` set money_type=#{moneyType},money_list=#{moneyList} where tunnel_pay_id=#{tunnelPayId} ")
    int update(TunnelPayRelation tunnelPayRelation);

    @Select("SELECT * FROM `tunnel_pay_relation` WHERE `tunnel_id`=#{tunnelId}")
    List<TunnelPayRelation> findByTunnelId(@Param("tunnelId") String tunnelId);
}
