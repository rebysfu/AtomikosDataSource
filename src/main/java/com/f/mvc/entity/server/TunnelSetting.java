package com.f.mvc.entity.server;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: bvsoo
 * Date: 2018/10/9
 * Time: 上午11:25
 * 通道配置
 */
@Data
public class TunnelSetting implements Serializable {
    private static final long serialVersionUID = -8959098223846888196L;
    private Long id;
    /**
     * 通道id
     */
    private String tunnelId;
    /**
     * 通道类型 枚举 见TunnelType
     */
    private Integer tunnelType;
    /**
     * 通道名称
     */
    private String tunnelName;
    /**
     * 是否开启
     */
    private Boolean turnOn;
    /**
     * 地域黑名单
     * ["北京","上海"]
     */
    private String areaBlackList;
    /**
     * ip黑名单
     * ["192.168.1.1","192.168.1.2"]
     */
    private String ipBlackList;
    /**
     * 渠道黑名单
     * ["ds","weilan"]
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
     * 附加参数
     */
    private String extendParams;
    /**
     * 描述
     */
    private String description;

    /**
     * 生成map配置 用于保存到redis缓存
     *
     * @return
     */
    public Map<String, String> toConfigMap() {
        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(id));
        result.put("tunnelId", tunnelId);
        result.put("tunnelName", tunnelName);
        result.put("desc", description);
        result.put("valid", String.valueOf(turnOn));
        result.put("mchId", mchId);
        result.put("tunnelKey", tunnelKey);
        result.put("payUrl", payUrl);
        result.put("notifyUrl", notifyUrl);
        result.put("extendParams", extendParams);
        if (areaBlackList != null)
            result.put("areaBlackList", areaBlackList);
        if (ipBlackList != null)
            result.put("ipBlackList", ipBlackList);
        if (chBlackList != null)
            result.put("chBlackList", chBlackList);
        return result;
    }

}
