package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.entity.Menu;
import com.f.mvc.service.MenuService;
import com.f.vo.ResponseVo;
import com.google.common.base.Strings;
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

/**
 * User: bvsoo
 * Date: 2018/8/20
 * Time: 下午7:50
 */
@RestController
@RequestMapping(value = "/menu")
@Api(value = "/menu", description = "菜单")
@Log4j2
public class MenuAction extends BaseAction {
    private static final long serialVersionUID = 7500351868032439916L;
    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询所有菜单")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        return ResponseVo.builder().data(menuService.findAllMenu()).build();
    }


    /**
     * 新增菜单
     *
     * @param request
     * @param url
     * @param name
     * @return
     */
    @ApiOperation(value = "/generate", notes = "新增菜单")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(HttpServletRequest request,
                               @RequestParam(value = "url", defaultValue = "") String url,
                               @RequestParam(value = "name", defaultValue = "") String name) {
        Long centerUserId = super.getUserId(request);
        if (Strings.isNullOrEmpty(url) || Strings.isNullOrEmpty(name))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Menu menu = new Menu();
        menu.setUrl(url);
        menu.setName(name);
        menu.setType(0);
        menu.setCreateUserId(centerUserId);
        menu.setCreateTime(new Date());
        int row = menuService.addNewMenu(menu);
        if (row < 1) return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().data(menu).build();
    }


    /**
     * 修改菜单
     *
     * @param request
     * @param id
     * @param url
     * @param name
     * @return
     */
    @ApiOperation(value = "/modify", notes = "修改菜单")
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo modify(HttpServletRequest request,
                             @RequestParam(value = "id", defaultValue = "0") long id,
                             @RequestParam(value = "url", defaultValue = "") String url,
                             @RequestParam(value = "name", defaultValue = "") String name) {
        Long centerUserId = super.getUserId(request);
        if (id < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Menu menu = menuService.findMenuById(id);
        if (menu == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (!Strings.isNullOrEmpty(url))
            menu.setUrl(url);
        if (!Strings.isNullOrEmpty(name))
            menu.setName(name);
        menu.setModifyUserId(centerUserId);
        menu.setModifyTime(new Date());
        int row = menuService.updateMenu(menu);
        if (row < 1) return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().data(menu).build();
    }


    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "/remove", notes = "删除菜单")
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo modify(@RequestParam(value = "id", defaultValue = "0") long id) {
        if (id < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        Menu menu = menuService.findMenuById(id);
        if (menu == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (menu.getType().intValue() == 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        int row = menuService.deleteMenuById(menu);
        if (row < 1) return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().data(menu).build();
    }


}
