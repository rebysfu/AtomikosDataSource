package com.f.configure;

import com.beust.jcommander.internal.Lists;
import com.f.configure.properties.AuthConfigure;
import com.f.mvc.entity.Menu;
import com.f.mvc.entity.SysRole;
import com.f.mvc.entity.User;
import com.f.mvc.service.MenuService;
import com.f.mvc.service.SysRoleService;
import com.f.mvc.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

/**
 * 初始化系统设置
 * User: bvsoo
 * Date: 2018/9/25
 * Time: 上午11:36
 */
@Configuration
@Order(1)
@Log4j2
public class DataRunnerConfigure implements CommandLineRunner {

    @Autowired
    private AuthConfigure authConfigure;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private SysRoleService sysRoleService;


    @Override
    public void run(String... args) throws Exception {
        this.initSysRole();
        this.initAdmin();
        this.initMenu();
    }


    /**
     * 初始化系统角色
     */
    private void initSysRole() {
        SysRole admin = sysRoleService.findSysRoleByRole("admin");
        Date date = new Date();
        if (admin == null) {
            SysRole sysRole = new SysRole();
            sysRole.setRole("admin");
            sysRole.setName("超级管理员");
            sysRole.setId(1L);
            sysRole.setCreateTime(date);
            sysRole.setCreateUserId(1L);
            sysRoleService.addSysRole(sysRole);
        }
    }


    /**
     * 初始化管理员
     */
    private void initAdmin() {
        //如果管理员不存在初始化管理员
        User manager = userService.findUserByAccount(authConfigure.getAccount());
        //管理员未初始化
        if (manager == null) {
            User user = new User();
            user.setId(1L);
            user.setName("administrator");
            user.setNickName("管理");
            user.setAccount(authConfigure.getAccount());
            user.setCreateTime(new Date());
            user.setCreateUserId(1L);
            user.setPassword(passwordEncoder.encode(authConfigure.getPassword()));
            user.setRemark("超级管理员");
            userService.addUser(user, new long[]{1L});
        }
    }

    /**
     * 初始化菜单
     */
    private void initMenu() {
        List<Menu> roleMenuList = menuService.findAllMenu();
        if (!roleMenuList.isEmpty()) return;
        List<String[]> list = Lists.newArrayList();
        list.add(new String[]{"/settings", "代理举报微信"});
        list.add(new String[]{"/settings", "代理充值记录"});
        list.add(new String[]{"/settings", "代理展示策略"});
        list.add(new String[]{"/settings", "代理用户管理"});
        list.add(new String[]{"/settings", "代理补货"});
        list.add(new String[]{"/settings", "作弊检测"});
        list.add(new String[]{"/settings", "充值窗口设置"});
        list.add(new String[]{"/settings", "充值记录"});
        list.add(new String[]{"/settings", "兑换分配"});
        list.add(new String[]{"/settings", "兑换处理记录"});
        list.add(new String[]{"/settings", "兑换审查"});
        list.add(new String[]{"/settings", "兑换窗口设置"});
        list.add(new String[]{"/settings", "兑换维护管理"});
        list.add(new String[]{"/settings", "兑换订单查询"});
        list.add(new String[]{"/settings", "嫌疑人列表"});
        list.add(new String[]{"/settings", "官网地址配置"});
        list.add(new String[]{"/settings", "客服列表"});
        list.add(new String[]{"/settings", "客服建议"});
        list.add(new String[]{"/settings", "客服提问间隔设置"});
        list.add(new String[]{"/settings", "引导游戏配置"});
        list.add(new String[]{"/settings", "微信监控域名管理"});
        list.add(new String[]{"/settings", "批量封号"});
        list.add(new String[]{"/settings", "推广管理"});
        list.add(new String[]{"/settings", "支付webview设置"});
        list.add(new String[]{"/settings", "支付宝兑换配比控制"});
        list.add(new String[]{"/settings", "支付宝实名列表"});
        list.add(new String[]{"/settings", "支付管理"});
        list.add(new String[]{"/settings", "支付订单上游查询"});
        list.add(new String[]{"/settings", "支付订单查询"});
        list.add(new String[]{"/settings", "新增用户查询"});
        list.add(new String[]{"/settings", "昵称查询"});
        list.add(new String[]{"/settings", "服务器IP及渠道包"});
        list.add(new String[]{"/settings", "注册渠道IP限制"});
        list.add(new String[]{"/settings", "消费记录"});
        list.add(new String[]{"/settings", "消费记录查询"});
        list.add(new String[]{"/settings", "添加新包"});
        list.add(new String[]{"/settings", "渠道管理"});
        list.add(new String[]{"/settings", "游戏类型限制"});
        list.add(new String[]{"/settings", "热更域名配置"});
        list.add(new String[]{"/settings", "玩家公告"});
        list.add(new String[]{"/settings", "玩家反馈"});
        list.add(new String[]{"/settings", "用户信息"});
        list.add(new String[]{"/settings", "用户管理"});
        list.add(new String[]{"/settings", "短信抬头配置"});
        list.add(new String[]{"/settings", "系统公告"});
        list.add(new String[]{"/settings", "苹果审核配置"});
        list.add(new String[]{"/settings", "足球信息"});
        list.add(new String[]{"/settings", "银行卡兑换查询"});
        Date date = new Date();
        for (String[] strings : list) {
            Menu menu = new Menu();
            menu.setUrl(strings[0]);
            menu.setName(strings[1]);
            menu.setType(1);
            menu.setCreateUserId(1L);
            menu.setCreateTime(date);
            menuService.addNewMenu(menu);
        }
    }
}
