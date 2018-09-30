package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.service.SysRoleService;
import com.f.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: bvsoo
 * Date: 2018/9/24
 * Time: 下午3:54
 */
@RestController
@RequestMapping(value = "/sysRole")
@Api(value = "/sysRole", description = "系统角色")
@Log4j2
public class SysRoleAction extends BaseAction {
    private static final long serialVersionUID = 8919455927038346653L;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 查询系统角色
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询系统角色")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        return ResponseVo.builder().data(sysRoleService.findAll()).build();
    }
}
