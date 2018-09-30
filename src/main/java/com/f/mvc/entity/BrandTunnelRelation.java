package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 上午10:56
 */
@Data
public class BrandTunnelRelation implements Serializable {
    private static final long serialVersionUID = 4373105918410299087L;
    /**
     * 数据库自增Id
     */
    private long id;
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
    private int weight;
    /**
     * 状态 1正常状态 2禁用
     */
    private int state;
}
