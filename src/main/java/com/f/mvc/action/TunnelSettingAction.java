package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 上午11:51
 */
@RestController
@RequestMapping(value = "/tunnelSetting")
@Api(value = "/tunnelSetting", description = "支付通道")
@Log4j2
public class TunnelSettingAction extends BaseAction {
    private static final long serialVersionUID = -4512303293369362923L;

    @ApiOperation(value = "/generate", notes = "新增支付通道")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(HttpServletRequest request,
                               @RequestParam(value = "tunnelTypeId") int tunnelTypeId,
                               @RequestParam(value = "tunnelId") int tunnelId,
                               @RequestParam(value = "weight") int weight) {
        return ResponseVo.builder().build();
    }


}
