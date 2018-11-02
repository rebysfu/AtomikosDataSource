package com.f.mvc.mapper.auth;

import com.f.mvc.entity.auth.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/7/18
 * Time: 下午3:51
 */
//@Mapper
@Repository
public interface UserRoleMapper {

    @Select("SELECT * FROM `tbl_user_role` WHERE `user_id`=#{userId}")
    List<UserRole> findByUserId(@Param(value = "userId") final Long userId);

    @Insert("INSERT INTO `tbl_user_role` (`sys_role_id`,`user_id`,`create_user_id`,`create_time`) VALUES(#{sysRoleId},#{userId},#{createUserId},#{createTime})")
    @Options(useGeneratedKeys = true)
    int addUserRole(UserRole userRole);

    @Delete("DELETE FROM `tbl_user_role` WHERE `id`=#{id}")
    int deleteUserRole(UserRole userRole);

    @Delete("DELETE FROM `tbl_user_role` WHERE `user_id`=#{userId} AND `sys_role_id` IN (${param}) ")
    int deleteUserRoleByParam(@Param(value = "userId") long userId, @Param(value = "param") String param);

    @Delete("DELETE FROM `tbl_user_role` WHERE `user_id`=#{userId}")
    int deleteUserRoleByUserId(@Param(value = "userId") Long userId);

}
