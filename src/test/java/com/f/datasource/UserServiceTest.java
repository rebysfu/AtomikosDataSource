package com.f.datasource;

import com.f.datasource.api.switcher.DataSourceSwitcher;
import com.f.datasource.api.switcher.DefaultDataSourceSwitcher;
import com.f.mvc.entity.User;
import com.f.mvc.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
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
        user.setAccount("ttttttttt");
        user.setId(1L);
        user.setPassword("123456");
        user.setCreateTime(new Date());
        userService.testTraction(user,new long[]{1});
       // userService.testTraction(user);
    }


    DataSourceSwitcher switcher = new DefaultDataSourceSwitcher();

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
    }

}
