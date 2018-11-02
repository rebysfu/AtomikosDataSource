package com.f.configure;

import com.beust.jcommander.internal.Lists;
import com.f.configure.properties.AuthConfigure;
import com.f.mvc.entity.auth.Menu;
import com.f.mvc.entity.auth.SysRole;
import com.f.mvc.entity.auth.User;
import com.f.mvc.service.auth.MenuService;
import com.f.mvc.service.auth.SysRoleService;
import com.f.mvc.service.auth.UserService;
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
public class DataRunnerConfigurer implements CommandLineRunner {

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
        list.add(new String[]{"/proxyReportWX", "代理举报微信"});
        list.add(new String[]{"/proxyRecharge", "代理充值记录"});
        list.add(new String[]{"/proxyShow", "代理展示策略"});
        list.add(new String[]{"/proxyUserAdmin", "代理用户管理"});
        list.add(new String[]{"/proxyReplenishment", "代理补货"});
        list.add(new String[]{"/cheatDetection", "作弊检测"});
        list.add(new String[]{"/rechargeSet", "充值窗口设置"});
        list.add(new String[]{"/rechargeRecord", "充值记录"});
        list.add(new String[]{"/exchangeAllot", "兑换分配"});
        list.add(new String[]{"/exchangeProRecord", "兑换处理记录"});
        list.add(new String[]{"/exchangeReview", "兑换审查"});
        list.add(new String[]{"/exchangeSet", "兑换窗口设置"});
        list.add(new String[]{"/exchangMaintain", "兑换维护管理"});
        list.add(new String[]{"/exchangOrder", "兑换订单查询"});
        list.add(new String[]{"/suspectList", "嫌疑人列表"});
        list.add(new String[]{"/officialUrl", "官网地址配置"});
        list.add(new String[]{"/serviceList", "客服列表"});
        list.add(new String[]{"/serviceProposal", "客服建议"});
        list.add(new String[]{"/serviceQuizInterval", "客服提问间隔设置"});
        list.add(new String[]{"/bootGame", "引导游戏配置"});
        list.add(new String[]{"/domainWX", "微信监控域名管理"});
        list.add(new String[]{"/batchTitle", "批量封号"});
        list.add(new String[]{"/extensionAdmin", "推广管理"});
        list.add(new String[]{"/payViewSet", "支付WebView设置"});
        list.add(new String[]{"/alipayExchangeRatio", "支付宝兑换配比控制"});
        list.add(new String[]{"/alipayRealName", "支付宝实名列表"});
        list.add(new String[]{"/payAdmin", "支付管理"});
        list.add(new String[]{"/payOrderUpstreamQuery", "支付订单上游查询"});
        list.add(new String[]{"/payOrderQuery", "支付订单查询"});
        list.add(new String[]{"/newUserQuery", "新增用户查询"});
        list.add(new String[]{"/nicknameQuery", "昵称查询"});
        list.add(new String[]{"/serverIPChannelBag", "服务器IP及渠道包"});
        list.add(new String[]{"/regChannelimitIP", "注册渠道IP限制"});
        list.add(new String[]{"/recordConsume", "消费记录"});
        list.add(new String[]{"/recordConsumeQuery", "消费记录查询"});
        list.add(new String[]{"/addNewBag", "添加新包"});
        list.add(new String[]{"/channelAdmin", "渠道管理"});
        list.add(new String[]{"/gameTypeLimit", "游戏类型限制"});
        list.add(new String[]{"/hotDomainConfig", "热更域名配置"});
        list.add(new String[]{"/playerNotice", "玩家公告"});
        list.add(new String[]{"/playerFeedback", "玩家反馈"});
        list.add(new String[]{"/userInfo", "用户信息"});
        list.add(new String[]{"/userAdmin", "用户管理"});
        list.add(new String[]{"/headerConfigSMS", "短信抬头配置"});
        list.add(new String[]{"/systemBulletin", "系统公告"});
        list.add(new String[]{"/appleAuditConfig", "苹果审核配置"});
        list.add(new String[]{"/infoFootball", "足球信息"});
        list.add(new String[]{"/bankCarExchangQuery", "银行卡兑换查询"});
        //auth菜单
        list.add(new String[]{"/payType", "支付类型"});
        list.add(new String[]{"/menus", "菜单管理"});
        list.add(new String[]{"/payConfig", "支付配置"});
        list.add(new String[]{"/brand", "品牌"});
        list.add(new String[]{"/roleMenu", "角色菜单"});


        Date date = new Date();
        for (String[] strings : list) {
            Menu menu = new Menu();
            menu.setUrl(strings[0]);
            menu.setName(strings[1]);
            menu.setType(1);
            menu.setCreateUserId(1L);
            menu.setCreateTime(date);
            menu.setModifyUserId(1L);
            menu.setModifyTime(date);
            menuService.addNewMenu(menu);
        }
    }
}
