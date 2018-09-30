package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 上午10:51
 */
@Data
public class TunnelPayRelation implements Serializable {
    private static final long serialVersionUID = 7255825422859559193L;
    /**
     * 数据库自增Id
     */
    private long id;
    /**
     * 通道支付关联id 由pay_type与tunnel_id组合而成
     */
    private String tunnelPayId;
    /**
     * 支付类型
     */
    private int payType;
    /**
     * 通道id
     */
    private String tunnelId;
    /**
     * 是否开启
     */
    private boolean turnOn;
    /**
     * 支付修正值
     */
    private int reduceMax;
    /**
     * 额度类型 定额1或者区间2
     */
    private int moneyType;
    /**
     * 定额或者区间 array json
     */
    private String moneyList;

}
