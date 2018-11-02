package com.f.vo;

import com.f.mvc.entity.server.BrandSetting;
import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;
import lombok.Builder;
import lombok.Data;

/**
 * @author rebysfu@gmail.com
 * @description：辅助完成笛卡尔积数据展示
 * @create 2018-10-17 下午6:24
 **/
@Builder
@Data
public class BrandTunnelRelationVO {
    /**
     * 品牌信息
     **/
    private BrandSetting brandSetting;
    /**
     * 品牌和支付类型关联关系表进行关联
     **/
    private BrandTunnelRelation brandTunnelRelation;
    /**
     * 支付类型配置信息表
     **/
    private TunnelPayRelation tunnelPayRelation;
    /*
     *通道信息表
     **/
    private TunnelSetting tunnelSetting;
    /**
     * 支付类型名称
     */
    private String payTypeName;

    /*
     *通道类型名称
     **/
    private String tunnelTypeName;

    /*
     * @Author rebysfu@gmail.com
     * @Description 先按照品牌排序，然后在按照支付类型排序
     * @Date 上午9:46 2018/10/26
     * @Param [b1, b2]
     * @return int
     **/
    public static int compareByBrandThenPayType(BrandTunnelRelationVO b1, BrandTunnelRelationVO b2) {
        if (b1.getBrandSetting().getBrand().equals(b2.getBrandSetting().getBrand())) {
            return b1.getPayTypeName().compareTo(b2.getPayTypeName());
        } else {
            return b1.getBrandSetting().getBrand().compareTo(b2.getBrandSetting().getBrand());
        }
    }
}
