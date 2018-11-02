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
 * Date: 2018/10/8
 * Time: 下午3:10
 */
@RestController
@RequestMapping(value = "/tunnelType")
@Api(value = "/tunnelType", description = "通道类型")
@Log4j2
public class TunnelTypeAction extends BaseAction {

    private static final long serialVersionUID = 5967658312024360858L;

    /**
     * 查询支付类型
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询通道类型")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        return ResponseVo.builder().data(TunnelType.getMapList()).build();
    }

    /**
     * 关闭支付类型
     *
     * @return
     */
    @ApiOperation(value = "/closer", notes = "关闭通道")
    @RequestMapping(value = "/closer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo closer() {
        return ResponseVo.builder().build();
    }
}
