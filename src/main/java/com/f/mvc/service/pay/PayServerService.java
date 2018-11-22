package com.f.mvc.service.pay;

import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;

/**
 * User: bvsoo
 * Date: 2018/10/20
 * Time: 上午10:48
 */
public interface PayServerService {

    void publish(TunnelSetting tunnel, boolean addTunnel);

    void publish(TunnelPayRelation tunnelPay);

    void publish(String tunnelId, int payType, BrandTunnelRelation brandTunnelRelation);

    void syncTunnel(String tunnelId);

    void updateBrandMapping();

}
