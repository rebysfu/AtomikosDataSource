package com.f.mvc.mapper.auth;

import com.f.mvc.entity.auth.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:23
 */
//@Mapper
public interface MenuMapper {

    @Insert("INSERT INTO `tbl_menu` (`url`, `name`,`type`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`)  VALUES (#{url},#{name},#{type},#{createUserId},#{createTime},#{modifyUserId},#{modifyTime})")
    @Options(useGeneratedKeys = true)
    int addNewMenu(Menu menu);

    @Select("SELECT * FROM `tbl_menu` WHERE `id`=#{id}")
    Menu findMenuById(@Param(value = "id") Long id);


    @Select("SELECT * FROM `tbl_menu`")
    List<Menu> findAllMenu();

    @Delete("DELETE FROM `tbl_menu` WHERE `id`=#{id}")
    int deleteMenuById(Menu menu);

    @Update("UPDATE `tbl_menu` SET `url`=#{url}, `name`=#{name}, `create_user_id`=#{createUserId}, `create_time`=#{createTime}, `modify_user_id`=#{modifyUserId}, `modify_time`=#{modifyTime} WHERE `id`=#{id}")
    int updateMenu(Menu menu);
}
