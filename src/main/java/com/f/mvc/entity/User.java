package com.f.mvc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 下午12:09
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2667076826887051019L;
    private Long id;
    private String name;//姓名
    private String nickName;//昵称
    private String phone;//电话
    private String account;//账号
    private String password;//密码
    private String remark;//备注
    private Long createUserId;//创建人
    private Date createTime;//创建时间
    private Long modifyUserId;//修改人
    private Date modifyTime;//修改时间
}
