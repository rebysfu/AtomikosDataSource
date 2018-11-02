package com.f.mvc.entity.server;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 中间表  品牌和支付类型关联关系表进行关联
 * User: bvsoo
 * Date: 2018/10/9
 * Time: 上午11:39
 * 品牌支付通道关联
 */
@Data
public class BrandTunnelRelation implements Serializable {
    private static final long serialVersionUID = 5850188186071480602L;
    /**
     * redis 品牌通道前缀
     */
    private static final String REDIS_TUNNEL_BRAND_PREFIX = "TUNNEL_BRAND_PRE_";
    private Long id;
    /**
     * 品牌id
     */
    private String brand;
    /**
     * 通道支付关联id
     */
    private String tunnelPayId;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 状态 1正常状态 2禁用
     */
    private Integer state;

    public Map<String, String> toConfigMap(int payType) {
        Map<String, String> result = new HashMap<>();
        String prefix = REDIS_TUNNEL_BRAND_PREFIX + payType + "_" + brand + "_";
        result.put(prefix + "id", String.valueOf(id));
        result.put(prefix + "tunnelPay", tunnelPayId);
        result.put(prefix + "weight", String.valueOf(weight));
        result.put(prefix + "state", String.valueOf(state));
        return result;
    }
}
