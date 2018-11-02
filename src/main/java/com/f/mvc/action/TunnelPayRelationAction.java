package com.f.mvc.action;

import com.alibaba.fastjson.JSON;
import com.f.base.BaseAction;
import com.f.enums.PayType;
import com.f.enums.StateEnum;
import com.f.enums.TunnelType;
import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;
import com.f.mvc.service.pay.PayServerService;
import com.f.mvc.service.server.BrandTunnelRelationService;
import com.f.mvc.service.server.TunnelPayRelationService;
import com.f.mvc.service.server.TunnelSettingService;
import com.f.vo.ResponseVo;
import com.f.vo.SelectTunnelPayTypeVO;
import com.f.vo.TunnelPayRelationVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-10 下午5:30
 **/
@RestController
@RequestMapping(value = "/tunnelPayRelation")
@Api(value = "/tunnelPayRelation", description = "通道支付关联配置")
@Validated
@Log4j2
public class TunnelPayRelationAction extends BaseAction {

    private static final String SEPARATOR = "_";

    private static final long serialVersionUID = 3803722811120086557L;
    @Autowired
    private TunnelPayRelationService tunnelPayRelationService;
    @Autowired
    private TunnelSettingService tunnelSettingService;
    @Autowired
    private BrandTunnelRelationService brandTunnelRelationService;
    @Autowired
    private PayServerService payServerService;

    @ApiOperation(value = "查询支付类型和通道绑定信息")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo queryTunnelPayInfo(@RequestParam(value = "tunnelType", defaultValue = "-1", required = false) int tunnelType) {
        List<TunnelPayRelationVO> list = Lists.newArrayList();
        List<TunnelSetting> tunnelSettings;
        if (tunnelType > -1) {
            tunnelSettings = tunnelSettingService.findByTunnelType(tunnelType);
        } else {
            tunnelSettings = tunnelSettingService.findAll();
        }
        tunnelSettings.stream().forEach(item -> {
            List<TunnelPayRelation> tunnelPayRelations = tunnelPayRelationService.findByTunnelId(item.getTunnelId());
            List<TunnelPayRelationVO> tunnelPayRelationVOS = tunnelPayRelations.stream().map(o -> {
                return TunnelPayRelationVO.builder()
                        .tunnelPayRelation(o)
                        .tunnelSetting(item)
                        .tunnelTypeName(TunnelType.getMap().get(item.getTunnelType()))
                        .payTypeName(PayType.getMap().get(o.getPayType()))
                        .build();
            }).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(tunnelPayRelations)) {
                list.add(TunnelPayRelationVO.builder()
                        .tunnelPayRelation(null)
                        .tunnelSetting(item)
                        .tunnelTypeName(TunnelType.getMap().get(item.getTunnelType()))
                        .payTypeName(null)
                        .build());
            } else {
                list.addAll(tunnelPayRelationVOS);
            }
        });
        return ResponseVo.builder().data(list).build();
    }

    /**
     * 关闭/开启通道支付
     *
     * @return
     */
    @ApiOperation(value = "关闭/开启通道支付", notes = "关闭/开启通道支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tunnelPayId", value = "通道支付关联id(由pay_type与tunnel_id组合而成)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "是否开启(false 关闭 true 开启)", required = true, dataType = "boolen")
    })
    @PostMapping(value = "/turnOperation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo turnOperation(@RequestParam(value = "tunnelPayId") String tunnelPayId,
                                    @RequestParam(value = "state") boolean state) {
        TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(tunnelPayId);
        if (tunnelPayRelation == null) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("记录不存在").build();
        }
        tunnelPayRelation.setTurnOn(state);
        int row = tunnelPayRelationService.turnOperation(tunnelPayRelation);
        if (row < 1) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        }
        payServerService.publish(tunnelPayRelation);
        return ResponseVo.builder().data(null).build();
    }

    /**
     * @param tunnelId
     * @param payType
     * @return
     */
    @ApiOperation(value = "/bind", notes = "支付通道绑定支付类型")
    @RequestMapping(value = "/bind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo bind(@RequestParam(value = "tunnelId") String tunnelId,
                           @RequestParam(value = "payType") int payType,
                           @RequestParam(value = "turnOn") boolean turnOn,
                           @RequestParam(value = "reduceMax") int reduceMax,
                           @RequestParam(value = "moneyType") int moneyType,
                           @RequestParam(value = "moneyList") int[] moneyList) {
        if (PayType.valueOf(payType) == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        TunnelPayRelation oldTunnelPayRelation = tunnelPayRelationService.findByPayTypeAndTunnelId(payType, tunnelId);
        if (oldTunnelPayRelation != null)
            return ResponseVo.builder().code(HttpStatus.OK).build();
        TunnelPayRelation tunnelPayRelation = new TunnelPayRelation();
        tunnelPayRelation.setPayType(payType);
        tunnelPayRelation.setTunnelId(tunnelId);
        tunnelPayRelation.setTunnelPayId(tunnelId + SEPARATOR + payType);
        tunnelPayRelation.setTurnOn(turnOn);
        tunnelPayRelation.setReduceMax(reduceMax);
        tunnelPayRelation.setMoneyType(moneyType);
        tunnelPayRelation.setMoneyList(JSON.toJSONString(moneyList));
        int row = tunnelPayRelationService.addNewTunnelPayRelation(tunnelPayRelation);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        payServerService.publish(tunnelPayRelation);
        return ResponseVo.builder().data(tunnelPayRelation).build();
    }

    /**
     * 查询所有关联通道
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询所有关联通道")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query(@RequestParam(value = "payType", defaultValue = "0", required = false) int payType,
                            @RequestParam("brand") String brand) {
        if (payType > 0) {
            //查询所有的关联通道
            List<TunnelPayRelation> tunnelPayRelations = tunnelPayRelationService.findByPayType(payType);
            //根据品牌查询出所有的品牌相关联的类型
            List<SelectTunnelPayTypeVO> list = tunnelPayRelations.stream().filter(Objects::nonNull).map(tunnelPayRelation -> {
                BrandTunnelRelation brandTunnelRelation = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, tunnelPayRelation.getTunnelPayId());
                TunnelSetting tunnelSetting = tunnelSettingService.findByTunnelId(tunnelPayRelation.getTunnelId());
                SelectTunnelPayTypeVO selectTunnelPayTypeVO = SelectTunnelPayTypeVO.builder()
                        .tunnelPayId(tunnelPayRelation.getTunnelPayId())
                        .tunnelName(tunnelSetting.getTunnelName())
                        .turnOn(brandTunnelRelation == null ? null : brandTunnelRelation.getState().intValue() == StateEnum.NORMAL.getCode().intValue() ? true : false)
                        .weight(brandTunnelRelation == null ? null : brandTunnelRelation.getWeight()).build();
                return selectTunnelPayTypeVO;
            }).collect(Collectors.toList());
            return ResponseVo.builder().data(list).build();
        } else {
            return ResponseVo.builder().data(tunnelPayRelationService.findAll()).build();
        }

    }

    @ApiOperation(value = "修改通道支付关联信息(修改支付)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tunnelPayId", value = "通道支付关联id(由pay_type与tunnel_id组合而成)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "moneyType", value = "支付类型", required = false, dataType = "int"),
            @ApiImplicitParam(name = "moneyList", value = "支付金额", required = false)
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo update(@RequestParam(value = "tunnelPayId") String tunnelPayId,
                             @RequestParam(value = "moneyType", required = false) int moneyType,
                             @RequestParam(value = "moneyList", required = false) int[] moneyList) {
        TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(tunnelPayId);
        if (tunnelPayRelation == null) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("记录不存在").build();
        }
        tunnelPayRelation.setMoneyList(JSON.toJSONString(moneyList));
        tunnelPayRelation.setMoneyType(moneyType);
        int row = tunnelPayRelationService.update(tunnelPayRelation);
        if (row < 1) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        }
        payServerService.publish(tunnelPayRelation);
        return ResponseVo.builder().data(null).build();
    }
}
