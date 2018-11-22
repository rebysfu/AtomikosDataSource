package com.f.mvc.entity.server;

import lombok.Data;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 上午10:59
 * 品牌
 */
@Data
public class BrandSetting implements Serializable {
    private static final long serialVersionUID = -2424298320207532028L;
    private Long id;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;

}
