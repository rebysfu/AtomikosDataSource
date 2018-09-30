package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.service.BrandService;
import com.f.vo.ResponseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User: bvsoo
 * Date: 2018/9/25
 * Time: 下午4:39
 */
@RestController
@RequestMapping(value = "/brand")
@Api(value = "/brand", description = "品牌")
@Log4j2
public class BrandAction extends BaseAction {
    private static final long serialVersionUID = -2961744427355052690L;

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询所有品牌")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        return ResponseVo.builder().data(Lists.newArrayList()).build();
    }


    /**
     * 查询所有品牌
     *
     * @return
     */
    @ApiOperation(value = "/generate", notes = "添加品牌")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(HttpServletRequest request,
                               @RequestParam(value = "brand") String brand,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "server") String server) {
        return ResponseVo.builder().build();
    }

}
