package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.entity.auth.Menu;
import com.f.mvc.entity.auth.RoleMenu;
import com.f.mvc.service.auth.MenuService;
import com.f.mvc.service.auth.RoleMenuService;
import com.f.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: bvsoo
 * Date: 2018/9/25
 * Time: 下午5:01
 */
@RestController
@RequestMapping(value = "/roleMenu")
@Api(value = "/roleMenu", description = "角色菜单")
@Log4j2
public class RoleMenuAction extends BaseAction {
    private static final long serialVersionUID = 1040758109498014818L;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;


    /**
     * 添加角色菜单
     *
     * @param request
     * @param roleId
     * @param menuId
     * @return
     */
    @ApiOperation(value = "/generate", notes = "添加角色菜单")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(HttpServletRequest request,
                               @RequestParam(value = "roleId") long roleId,
                               @RequestParam(value = "menuId") long menuId) {
        if (roleId <= 1 || menuId < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Menu menu = menuService.findMenuById(menuId);
        if (menu == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(String.valueOf(roleId));
        Long centerUserId = super.getUserId(request);
        synchronized (lock) {
            List<RoleMenu> roleMenus = roleMenuService.findRoleMenuByRoleId(roleId);
            Optional<RoleMenu> optionalRoleMenu = roleMenus.stream().filter(o -> o.getMenuId().longValue() == menuId).findFirst();
            if (optionalRoleMenu.isPresent())
                return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setCreateUserId(centerUserId);
            roleMenu.setCreateTime(new Date());
            int row = roleMenuService.addNewRow(roleMenu);
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().data(roleMenu).build();
        }

    }


    /**
     * 删除角色菜单
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @ApiOperation(value = "/remove", notes = "删除角色菜单")
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo remove(@RequestParam(value = "roleId") long roleId,
                             @RequestParam(value = "menuId") long menuId) {
        if (roleId <= 1 || menuId < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Menu menu = menuService.findMenuById(menuId);
        if (menu == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(String.valueOf(roleId));
        synchronized (lock) {
            List<RoleMenu> roleMenus = roleMenuService.findRoleMenuByRoleId(roleId);
            Optional<RoleMenu> optionalRoleMenu = roleMenus.stream().filter(o -> o.getMenuId().longValue() == menuId).findFirst();
            if (!optionalRoleMenu.isPresent())
                return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            int row = roleMenuService.deleteRoleMenuById(optionalRoleMenu.get());
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().build();
        }

    }

    /**
     * 查询角色菜单
     *
     * @param roleId
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询角色菜单")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query(@RequestParam(value = "roleId", defaultValue = "0") long roleId) {
        if (roleId < 0)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (roleId <= 0)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        List<RoleMenu> roleMenus = roleMenuService.findRoleMenuByRoleId(roleId);
        List<Menu> menus = roleMenus.stream().map(o -> menuService.findMenuById(o.getMenuId())).collect(Collectors.toList());
        return ResponseVo.builder().data(menus).build();
    }
}
