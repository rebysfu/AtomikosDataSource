package com.f.mvc.action;

import com.alibaba.fastjson.JSON;
import com.f.base.BaseAction;
import com.f.helper.JSONHelper;
import com.f.mvc.entity.server.TunnelSetting;
import com.f.mvc.service.pay.PayServerService;
import com.f.mvc.service.server.TunnelSettingService;
import com.f.vo.ResponseVo;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 上午11:51
 */
@RestController
@RequestMapping(value = "/tunnelSetting")
@Api(value = "/tunnelSetting", description = "支付通道")
@Validated
@Log4j2
public class TunnelSettingAction extends BaseAction {
    private static final long serialVersionUID = -4512303293369362923L;
    @Autowired
    private TunnelSettingService tunnelSettingService;
    @Autowired
    private PayServerService payServerService;

    /**
     * @param tunnelType
     * @param tunnelId
     * @param tunnelName
     * @param areaBlack
     * @param ipBlack
     * @param chBlack
     * @param mchId
     * @param tunnelKey
     * @param payUrl
     * @param notifyUrl
     * @param extendParams
     * @param description
     * @return
     */
    @ApiOperation(value = "/generate", notes = "新增支付通道")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(@RequestParam(value = "tunnelId") String tunnelId,
                               @RequestParam(value = "tunnelType") int tunnelType,
                               @RequestParam(value = "tunnelName") String tunnelName,
                               @RequestParam(value = "areaBlack", required = false) String[] areaBlack,
                               @RequestParam(value = "ipBlack", required = false) String[] ipBlack,
                               @RequestParam(value = "chBlack", required = false) String[] chBlack,
                               @RequestParam(value = "turnOn") boolean turnOn,
                               @RequestParam(value = "mchId") String mchId,
                               @RequestParam(value = "tunnelKey") String tunnelKey,
                               @RequestParam(value = "payUrl") String payUrl,
                               @RequestParam(value = "notifyUrl") String notifyUrl,
                               @RequestParam(value = "extendParams", required = false) String extendParams,
                               @RequestParam(value = "description") String description) {

        if (Strings.isNullOrEmpty(tunnelId))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (!Strings.isNullOrEmpty(extendParams) && !JSONHelper.verifyString(extendParams))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        TunnelSetting oldTunnelSetting = tunnelSettingService.findByTunnelId(tunnelId);
        if (oldTunnelSetting != null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        TunnelSetting tunnelSetting = new TunnelSetting();
        tunnelSetting.setTunnelId(tunnelId);
        tunnelSetting.setTunnelType(tunnelType);
        tunnelSetting.setTunnelName(tunnelName);
        tunnelSetting.setTurnOn(turnOn);
        tunnelSetting.setAreaBlackList(JSON.toJSONString(areaBlack));
        tunnelSetting.setIpBlackList(JSON.toJSONString(ipBlack));
        tunnelSetting.setChBlackList(JSON.toJSONString(chBlack));
        tunnelSetting.setMchId(mchId);
        tunnelSetting.setTunnelKey(tunnelKey);
        tunnelSetting.setPayUrl(payUrl);
        tunnelSetting.setNotifyUrl(notifyUrl);
        tunnelSetting.setExtendParams(extendParams);
        tunnelSetting.setDescription(description);
        int row = tunnelSettingService.addNewTunnelSetting(tunnelSetting);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        payServerService.publish(tunnelSetting, true);
        return ResponseVo.builder().data(tunnelSetting).build();
    }


    /**
     * 查询所有支付通道
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询通道类型下的通道")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query(@RequestParam(value = "tunnelType", defaultValue = "-1", required = false) int tunnelType) {
        if (tunnelType > -1)
            return ResponseVo.builder().data(tunnelSettingService.findByTunnelType(tunnelType)).build();
        else
            return ResponseVo.builder().data(tunnelSettingService.findAll()).build();
    }

    /**
     * 关闭开启通道
     *
     * @return
     */
    @ApiOperation(value = "/turnOperation", notes = "关闭/开启通道")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tunnelId", value = "通道Id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "是否开启(false 关闭 true 开启)", required = true, dataType = "boolen")
    })
    @PostMapping(value = "/turnOperation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo turnOperation(@RequestParam(value = "tunnelId") String tunnelId,
                                    @RequestParam(value = "state") boolean state) {
        TunnelSetting tunnelSetting = tunnelSettingService.findByTunnelId(tunnelId);
        if (tunnelSetting == null) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("记录不存在！").build();
        }
        tunnelSetting.setTurnOn(state);
        int row = tunnelSettingService.turnOperation(tunnelSetting);
        if (row < 1) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        }
        payServerService.publish(tunnelSetting, false);
        return ResponseVo.builder().build();
    }


    /**
     * 修改支付通道基础数据
     *
     * @return
     */
    @ApiOperation(value = "/modify", notes = "修改支付通道基础数据")
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo modify(@RequestParam(value = "tunnelId") String tunnelId,
                             @RequestParam(value = "tunnelType", defaultValue = "0") int tunnelType,
                             @RequestParam(value = "tunnelName", required = false) String tunnelName,
                             @RequestParam(value = "areaBlack", required = false) String[] areaBlack,
                             @RequestParam(value = "ipBlack", required = false) String[] ipBlack,
                             @RequestParam(value = "chBlack", required = false) String[] chBlack,
                             @RequestParam(value = "mchId", required = false) String mchId,
                             @RequestParam(value = "tunnelKey", required = false) String tunnelKey,
                             @RequestParam(value = "payUrl", required = false) String payUrl,
                             @RequestParam(value = "notifyUrl", required = false) String notifyUrl,
                             @RequestParam(value = "extendParams", required = false) String extendParams,
                             @RequestParam(value = "description", required = false) String description) {
        if (Strings.isNullOrEmpty(tunnelId))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        TunnelSetting tunnelSetting = tunnelSettingService.findByTunnelId(tunnelId);
        if (!Strings.isNullOrEmpty(extendParams) && !JSONHelper.verifyString(extendParams))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (tunnelSetting == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        if (tunnelType > 0)
            tunnelSetting.setTunnelType(tunnelType);
        if (!Strings.isNullOrEmpty(tunnelName))
            tunnelSetting.setTunnelName(tunnelName);
        if (areaBlack != null && areaBlack.length > 0)
            tunnelSetting.setAreaBlackList(JSON.toJSONString(areaBlack));
        if (ipBlack != null && ipBlack.length > 0)
            tunnelSetting.setIpBlackList(JSON.toJSONString(ipBlack));
        if (chBlack != null && chBlack.length > 0)
            tunnelSetting.setChBlackList(JSON.toJSONString(chBlack));
        if (!Strings.isNullOrEmpty(mchId))
            tunnelSetting.setMchId(mchId);
        if (!Strings.isNullOrEmpty(tunnelKey))
            tunnelSetting.setTunnelKey(tunnelKey);
        if (!Strings.isNullOrEmpty(payUrl))
            tunnelSetting.setPayUrl(payUrl);
        if (!Strings.isNullOrEmpty(notifyUrl))
            tunnelSetting.setNotifyUrl(notifyUrl);
        if (!Strings.isNullOrEmpty(description))
            tunnelSetting.setDescription(description);
        tunnelSetting.setExtendParams(JSONHelper.toJSONString(extendParams));
        int row = tunnelSettingService.updateTunnelSetting(tunnelSetting);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        payServerService.publish(tunnelSetting, false);
        return ResponseVo.builder().data(tunnelSetting).build();
    }

}
