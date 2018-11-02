package com.f.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-23 上午9:48
 **/
@Data
@Builder
public class SelectTunnelPayTypeVO {
    /**
     * 通道支付关联id
     */
    private String tunnelPayId;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 通道名称
     */
    private String tunnelName;
    /**
     * 是否开启
     */
    private Boolean turnOn;
}
