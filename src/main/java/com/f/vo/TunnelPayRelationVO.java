package com.f.vo;

import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;
import lombok.Builder;
import lombok.Data;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-15 下午6:40
 **/
@Builder
@Data
public class TunnelPayRelationVO {
    /**
     * 支付类型配置信息表
     */
    private TunnelPayRelation tunnelPayRelation;
    /**
     * 通道信息表
     */
    private TunnelSetting tunnelSetting;
    /**
     * 支付类型名称
     */
    private String payTypeName;

    /**
     * 通道类型名称
     */
    private String tunnelTypeName;
}
