package com.f.datasource;

import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.f.mvc.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-22 下午6:12
 **/
public class UserServiceTest extends ServiceBaseTest {
    @Resource
    private UserService userService;

    @Test
    public void findUserById() {
        userService.findUserById(2L);

    }

    @Test
    public void addUser() {
        User user = new User();
        user.setAccount("admin");
        user.setId(1L);
        UserRole userRole=new UserRole();
       long[] roleIds={1,2,3};
        userService.testTraction(user,roleIds);
    }


    public static void main(String[] args) {



    }
 /*   DataSourceSwitcher switcher = new DefaultDataSourceSwitcher();

    @Test
    public void testChangeSwitcher() {
        switcher.use("one");//切换为test
        assertEquals(switcher.currentDataSourceId(), "one");
        switcher.use("two");//切换为test2
        assertEquals(switcher.currentDataSourceId(), "two");

        switcher.useDefault();//切换默认数据源
        assertTrue(switcher.currentDataSourceId() == null);

        switcher.useLast(); //切换为上一次使用的数据源(test2)
        assertEquals(switcher.currentDataSourceId(), "two");

        switcher.useLast(); //切换为上一次使用的数据源(test)
        assertEquals(switcher.currentDataSourceId(), "one");

        switcher.useLast(); //切换为上一次书用的数据源(无,默认为default)
        assertTrue(switcher.currentDataSourceId() == null);

        switcher.useLast();
        assertTrue(switcher.currentDataSourceId() == null);
        ;
    }*/

}
