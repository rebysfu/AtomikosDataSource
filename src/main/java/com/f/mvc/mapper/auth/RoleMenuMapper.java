package com.f.mvc.mapper.auth;

import com.f.mvc.entity.RoleMenu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:35
 */
@Repository
@Mapper
public interface RoleMenuMapper {

    @Insert("INSERT INTO `tbl_role_menu` (`id`,`menu_id`, `role_id`,`create_user_id`,`create_time`)  VALUES (#{id},#{menuId},#{roleId},#{createUserId},#{createTime})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int addNewRow(RoleMenu roleMenu);

    @Delete("DELETE FROM `tbl_role_menu` WHERE `id`=#{id}")
    int deleteRoleMenuById(RoleMenu roleMenu);

    @Delete("DELETE FROM `tbl_role_menu` WHERE `menu_id`=#{menuId}")
    int deleteRoleMenuByMenuId(@Param(value = "menuId") Long menuId);

    @Select("SELECT * FROM `tbl_role_menu` WHERE `role_id`=#{roleId}")
    List<RoleMenu> findRoleMenuByRoleId(@Param(value = "roleId") Long roleId);

    @Select("SELECT * FROM `tbl_role_menu` WHERE `id`=#{id}")
    RoleMenu findRoleMenuById(@Param(value = "id") Long id);

    @Select("SELECT * FROM `tbl_role_menu`")
    List<RoleMenu> findAllRoleMenu();

}
