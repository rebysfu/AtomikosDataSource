package com.f.mvc.entity.server;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 中间表  通道和支付类型关联关系表
 * User: bvsoo
 * Date: 2018/10/9
 * Time: 上午11:35
 */
@Data
public class TunnelPayRelation implements Serializable {
    private static final long serialVersionUID = -4547917039524768607L;

    /**
     * 支付类型通道配置前缀
     */
    private static final String REDIS_TUNNEL_PAY_PREFIX = "TUNNEL_PAY_PRE_";


    /**
     * 自增id
     */
    private Long id;
    /**
     * 通道支付关联id(由pay_type与tunnel_id组合而成)
     */
    private String tunnelPayId;
    /**
     * 支付类型
     */
    private Integer payType;
    /**
     * 通道id
     */
    private String tunnelId;
    /**
     * 是否开启
     */
    private Boolean turnOn;
    /**
     * 支付修正值
     */
    private Integer reduceMax;
    /**
     * 额度类型 定额1或者区间2
     */
    private Integer moneyType;
    /**
     * 定额或者区间
     * [1,3,4]   [10,40,50,100]
     */
    private String moneyList;

    /**
     * 转换成写配置Map
     *
     * @return
     */
    public Map<String, String> toConfigMap() {
        Map<String, String> result = new HashMap<>();
        String prefix = REDIS_TUNNEL_PAY_PREFIX + payType + "_";
        result.put(prefix + "id", String.valueOf(id));
        result.put(prefix + "turnOn", String.valueOf(turnOn));
        result.put(prefix + "moneyType", String.valueOf(moneyType));
        if (moneyList != null)
            result.put(prefix + "money", moneyList);
        result.put(prefix + "reduce", String.valueOf(reduceMax));
        return result;
    }
}
