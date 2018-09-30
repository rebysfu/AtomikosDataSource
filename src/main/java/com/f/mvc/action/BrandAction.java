package com.f.mvc.action;

import com.f.base.BaseAction;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
