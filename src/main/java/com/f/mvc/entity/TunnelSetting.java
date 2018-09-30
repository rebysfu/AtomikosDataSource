package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 上午10:42
 */
@Data
public class TunnelSetting implements Serializable {
    private static final long serialVersionUID = 2819057959114233354L;
    /**
     * 数据库自增Id
     */
    private long id;
    /**
     * 通道id
     */
    private int tunnelId;
    /**
     * 通道类型
     */
    private int tunnelType;
    /**
     * 通道名称
     */
    private String tunnelName;
    /**
     * 是否开启
     */
    private boolean turnOn;
    /**
     * 地域黑名单
     */
    private String areaBlackList;
    /**
     * ip黑名单
     */
    private String ipBlackList;
    /**
     * 渠道黑名单
     */
    private String chBlackList;
    /**
     * 商户id
     */
    private String mchId;
    /**
     * 渠道密钥
     */
    private String tunnelKey;
    /**
     * 支付url
     */
    private String payUrl;
    /**
     * 回调url
     */
    private String notifyUrl;
    /**
     * 额外参数
     */
    private String extendParams;
    /**
     * 描述
     */
    private String description;
}
