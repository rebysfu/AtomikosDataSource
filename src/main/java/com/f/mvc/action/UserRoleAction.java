package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.entity.auth.SysRole;
import com.f.mvc.entity.auth.User;
import com.f.mvc.entity.auth.UserRole;
import com.f.mvc.service.auth.SysRoleService;
import com.f.mvc.service.auth.UserRoleService;
import com.f.mvc.service.auth.UserService;
import com.f.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: bvsoo
 * Date: 2018/9/24
 * Time: 下午3:55
 */
@RestController
@RequestMapping(value = "/userRole")
@Api(value = "/userRole", description = "用户角色")
@Log4j2
public class UserRoleAction extends BaseAction {
    private static final long serialVersionUID = 222958499115366813L;


    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 查询用户角色
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询用户角色")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query(HttpServletRequest request) {
        Long centerUserId = super.getUserId(request);
        List<UserRole> userRoles = userRoleService.findByUserId(centerUserId);
        List<SysRole> sysRoles = userRoles.stream().map(o -> sysRoleService.findSysRoleById(o.getSysRoleId())).collect(Collectors.toList());
        return ResponseVo.builder().data(sysRoles).build();
    }


    /**
     * 管理员查询用户的角色
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "/manager/query", notes = "管理员查询用户角色")
    @RequestMapping(value = "/manager/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo managerQuery(@RequestParam(value = "userId", defaultValue = "0") long userId) {
        if (userId < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        List<UserRole> userRoles = userRoleService.findByUserId(userId);
        List<SysRole> sysRoles = userRoles.stream().map(o -> sysRoleService.findSysRoleById(o.getSysRoleId())).collect(Collectors.toList());
        return ResponseVo.builder().data(sysRoles).build();
    }


    /**
     * 添加角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @ApiOperation(value = "/generate", notes = "添加角色")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(@RequestParam(value = "userId") long userId,
                               @RequestParam(value = "roleId") long roleId) {
        if (userId < 1 || roleId < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (userId == 1 && roleId == 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        User user = userService.findUserById(userId);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        SysRole sysRole = sysRoleService.findSysRoleById(roleId);
        if (sysRole == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(user.getAccount());
        synchronized (lock) {
            List<UserRole> userRoles = userRoleService.findByUserId(userId);
            Optional<UserRole> optionalUserRole = userRoles.stream().filter(o -> o.getSysRoleId().longValue() == roleId).findFirst();
            if (optionalUserRole.isPresent()) return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            UserRole userRole = new UserRole();
            userRole.setSysRoleId(roleId);
            userRole.setUserId(userId);
            userRole.setCreateTime(new Date());
            int row = userRoleService.addUserRole(userRole);
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().build();
        }

    }


    /**
     * 移除角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @ApiOperation(value = "/remove", notes = "移除角色")
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo remove(@RequestParam(value = "userId") long userId,
                             @RequestParam(value = "roleId") long roleId) {
        if (userId < 1 || roleId < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (userId == 1 && roleId == 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        User user = userService.findUserById(userId);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        SysRole sysRole = sysRoleService.findSysRoleById(roleId);
        if (sysRole == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(user.getAccount());
        synchronized (lock) {
            List<UserRole> userRoles = userRoleService.findByUserId(userId);
            Optional<UserRole> optionalUserRole = userRoles.stream().filter(o -> o.getSysRoleId().longValue() == roleId).findFirst();
            if (!optionalUserRole.isPresent()) return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            int row = userRoleService.deleteUserRole(optionalUserRole.get());
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().build();
        }

    }


    /**
     * 批量操作用户角色
     *
     * @param userId
     * @param newly
     * @param waste
     * @return
     */
    @ApiOperation(value = "/batch", notes = "批量操作用户角色")
    @RequestMapping(value = "/batch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo batch(@ApiParam(value = "userId", name = "用户Id") @RequestParam(value = "userId", defaultValue = "0") long userId,
                            @ApiParam(value = "newly", name = "新增角色") @RequestParam(value = "newly") long[] newly,
                            @ApiParam(value = "waste", name = "移除角色") @RequestParam(value = "waste") long[] waste) throws Exception {
        if (userId < 1 || (newly.length < 1 && waste.length < 1))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (userId == 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        User user = userService.findUserById(userId);
        if (user == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        String lock = super.generateMutex(user.getAccount());
        if (newly == null) newly = new long[0];
        synchronized (lock) {
            List<UserRole> userRoles = userRoleService.findByUserId(userId);
            long[] finalNewly = newly;
            Optional<UserRole> optionalUserRole = userRoles.stream().filter(o -> {
                Long roleId = o.getSysRoleId();
                int row = Arrays.binarySearch(finalNewly, roleId.longValue());
                return row > 0;
            }).findFirst();
            if (optionalUserRole.isPresent())
                return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();

            int row = userRoleService.userRoleBatch(userId, newly, waste);
            if (row < 1)
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseVo.builder().data(userRoleService.findByUserId(userId)).build();

        }
    }

}
