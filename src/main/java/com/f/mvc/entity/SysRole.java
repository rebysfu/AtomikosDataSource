package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午12:06
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 8135893615316959848L;
    private Long id;//自增加id
    private String role;//角色
    private String name;//角色名称
    private Long createUserId;//创建人
    private Date createTime;//创建时间
}
