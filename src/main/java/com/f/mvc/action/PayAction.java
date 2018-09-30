package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.enums.TunnelType;
import com.f.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: bvsoo
 * Date: 2018/9/25
 * Time: 下午4:38
 */
@RestController
@RequestMapping(value = "/pay")
@Api(value = "/pay", description = "支付")
@Log4j2
public class PayAction extends BaseAction {
    private static final long serialVersionUID = 2784159884946028349L;


    /**
     * 查询支付类型
     *
     * @return
     */
    @ApiOperation(value = "/tunnelType", notes = "查询支付类型")
    @RequestMapping(value = "/tunnelType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo tunnelType() {
        return ResponseVo.builder().data(TunnelType.getCache()).build();
    }
}
