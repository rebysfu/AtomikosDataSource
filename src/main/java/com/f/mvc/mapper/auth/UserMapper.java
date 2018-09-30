package com.f.mvc.mapper.auth;

import com.f.mvc.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/7/18
 * Time: 下午3:56
 */
@Repository
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `tbl_users` WHERE `account`=#{account}")
    User findUserByAccount(@Param(value = "account") final String account);

    @Select("SELECT * FROM `tbl_users` WHERE `id`=#{id}")
    User findUserById(@Param(value = "id") final Long id);

    @Insert("INSERT INTO `tbl_users` (`account`, `create_time`, `create_user_id`, `modify_user_id`,`modify_time`, `name`, `nick_name`, `password`, `phone`, `remark`) VALUES (#{account},#{createTime},#{createUserId},#{modifyUserId},#{modifyTime},#{name},#{nickName},#{password},#{phone},#{remark})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int addUser(User user);

    @Delete("DELETE FROM `tbl_users` WHERE `id`=#{id}")
    int deleteUser(User user);

    @Update("UPDATE `tbl_users` SET `account`=#{account}, `create_time`=#{createTime}, `create_user_id`=#{createUserId}, `modify_time`=#{modifyTime}, `name`=#{name}, `nick_name`=#{nickName}, `password`=#{password}, `phone`=#{phone}, `remark`=#{remark} WHERE `id`=#{id}")
    int modifyUser(User user);

    @Select({"<script> SELECT * FROM `tbl_users` <where> `id`>1" +
            "<if test='keyword!=null'> AND `account` LIKE CONCAT(#{keyword},'%') OR `name` LIKE CONCAT(#{keyword},'%')</if>" +
            "</where>" +
            "</script>"
    })
    List<User> findUserByParam(@Param(value = "keyword") String keyword, Page<User> page);

    @Select("SELECT * FROM `tbl_users` WHERE `account` LIKE CONCAT(#{param},'%') OR `name` LIKE CONCAT(#{param},'%')")
    List<User> findUserLikeParam(@Param(value = "param") String param);

}
