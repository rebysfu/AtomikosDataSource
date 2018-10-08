package com.f.datasource;

import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.f.mvc.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-22 下午6:12
 **/
public class UserServiceTest extends ServiceBaseTest {
    @Resource
    private UserService userService;

    public static void main(String[] args) {


    }

    @Test
    public void findUserById() {
        userService.findUserById(2L);

    }
    @Test
    public void addUser() {
        User user=new User();
        user.setCreateTime(new Date());
        user.setAccount("test111");
        user.setPassword("12345");
        user.setNickName("dddd");
        userService.addUser(user,new long[]{1L,2L,3L});

    }



}
