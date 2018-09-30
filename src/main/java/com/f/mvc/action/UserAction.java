package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.entity.Menu;
import com.f.mvc.entity.RoleMenu;
import com.f.mvc.entity.User;
import com.f.mvc.entity.UserRole;
import com.f.mvc.service.MenuService;
import com.f.mvc.service.RoleMenuService;
import com.f.mvc.service.UserRoleService;
import com.f.mvc.service.UserService;
import com.f.vo.ResponseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: bvsoo
 * Date: 2018/9/24
 * Time: 下午3:24
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "/user", description = "用户")
public class UserAction extends BaseAction {
    private static final long serialVersionUID = -7366850015365031991L;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    /**
     * 查询账号的菜单
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "/test", notes = "测试多线程高并发")
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo test(HttpServletRequest request,
                           @RequestParam(value = "account", defaultValue = "") String account,
                           @RequestParam(value = "password", defaultValue = "") String password,
                           @RequestParam(value = "roleIds") long[] roleIds) {

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        userService.testTraction(user, roleIds);
        return ResponseVo.builder().data(user).build();
    }


    /**
     * 查询账号的菜单
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "/menus", notes = "查询菜单")
    @RequestMapping(value = "/menus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo menus(HttpServletRequest request) {
        Long centerUserId = super.getUserId(request);
        List<Menu> menus = Lists.newArrayList();
        List<UserRole> userRoles = userRoleService.findByUserId(centerUserId);
        userRoles.forEach(o -> {
            List<RoleMenu> roleMenus = roleMenuService.findRoleMenuByRoleId(o.getSysRoleId());
            roleMenus.forEach(a -> menus.add(menuService.findMenuById(a.getMenuId())));
        });
        List<Menu> menuList = menus.stream().distinct().sorted(Comparator.comparing(Menu::getId)).collect(Collectors.toList());
        return ResponseVo.builder().data(menuList).build();
    }


    /**
     * 查询所有用户
     *
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询所有用户")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo managerQuery(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "0") int size,
                                   @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (page < 1 || size < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Page<User> userPage = PageHelper.startPage(page, size);
        userService.findUserByParam(Strings.isNullOrEmpty(keyword) ? null : keyword, userPage);
        return ResponseVo.builder().data(userPage).build();
    }


    /**
     * 添加新用户
     *
     * @param account
     * @param password
     * @return
     */
    @ApiOperation(value = "/generate", notes = "添加新用户")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(HttpServletRequest request,
                               @RequestParam(value = "account", defaultValue = "") String account,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               @RequestParam(value = "roleIds") long[] roleIds) {
        if (Strings.isNullOrEmpty(account) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(account.trim()) || Strings.isNullOrEmpty(password.trim()) || roleIds.length < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(String.valueOf(account.trim()));
        Long userId = super.getUserId(request);
        User user = new User();
        synchronized (lock) {
            User old = userService.findUserByAccount(account.trim());
            if (old != null)
                return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            user.setAccount(account.trim());
            user.setName(account);
            user.setPassword(passwordEncoder.encode(password.trim()));
            user.setCreateUserId(userId);
            user.setCreateTime(new Date());
            int row = userService.addUser(user, roleIds);
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().data(user).build();

        }
    }

    /**
     * 管理员重置密码
     *
     * @param id
     * @param password
     * @return
     */
    @ApiOperation(value = "/modify/password", notes = "重置用户密码")
    @RequestMapping(value = "/modify/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo modifyPassword(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "password") String password) {
        if (Strings.isNullOrEmpty(password))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        User user = userService.findUserById(id);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        user.setPassword(passwordEncoder.encode(password));
        int row = userService.modifyUser(user);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().build();
    }


    /**
     * 删除账号
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "/remove", notes = "删除账号")
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo removeUser(@RequestParam(value = "id") Long id) {
        User user = userService.findUserById(id);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        int row = userService.removeUser(user);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().build();
    }


    /**
     * 修改密码
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @ApiOperation(value = "/change/password", notes = "修改密码")
    @RequestMapping(value = "/change/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo changePassword(HttpServletRequest request,
                                     @RequestParam(value = "oldPassword") String oldPassword,
                                     @RequestParam(value = "newPassword") String newPassword,
                                     @RequestParam(value = "confirmPassword") String confirmPassword) {
        if (Strings.isNullOrEmpty(oldPassword) || Strings.isNullOrEmpty(newPassword) || Strings.isNullOrEmpty(confirmPassword))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        long userId = super.getUserId(request);
        User user = userService.findUserById(userId);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (!confirmPassword.equals(newPassword))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        user.setPassword(passwordEncoder.encode(confirmPassword));
        int row = userService.modifyUser(user);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().build();
    }


}
