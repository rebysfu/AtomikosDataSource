package com.f.mvc.entity.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:20
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -2114737749281506039L;
    private Long id;
    private String url;
    private String name;
    private Integer type;//1/0 固化菜单/添加菜单
    private Long createUserId;
    private Date createTime;
    private Long modifyUserId;
    private Date modifyTime;
}
