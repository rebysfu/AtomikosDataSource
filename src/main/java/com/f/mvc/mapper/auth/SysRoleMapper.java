package com.f.mvc.mapper.auth;

import com.f.mvc.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/7/18
 * Time: 下午3:38
 */
//@Mapper
public interface SysRoleMapper {

    @Select("SELECT * FROM `tbl_sys_role`")
    List<SysRole> findAllSysRole();

    @Select("SELECT * FROM `tbl_sys_role` WHERE `id`=#{id}")
    SysRole findSysRoleById(@Param(value = "id") final Long id);

    @Select("SELECT * FROM `tbl_sys_role` WHERE `role`=#{role}")
    SysRole findSysRoleByRole(@Param(value = "role") final String role);

    @Insert("INSERT INTO `tbl_sys_role` (`role`,`name`,`create_user_id`,`create_time`) VALUES (#{role},#{name},#{createUserId},#{createTime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int addSysRole(SysRole role);

}
