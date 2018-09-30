package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午2:00
 */
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1653932316847190291L;
    private Long id;//id
    private Long sysRoleId;//角色id
    private Long userId;//用户id
    private Long createUserId;//创建人
    private Date createTime;//创建时间
}
