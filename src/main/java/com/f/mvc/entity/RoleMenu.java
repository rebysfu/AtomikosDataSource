package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:22
 */
@Data
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 272635626916157706L;
    private Long id;
    private Long menuId;//菜单id
    private Long roleId;//角色Id
    private Long createUserId;//创建人
    private Date createTime;//创建时间
}
