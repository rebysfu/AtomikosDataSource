package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.enums.PayType;
import com.f.enums.StateEnum;
import com.f.enums.TunnelType;
import com.f.mvc.entity.server.BrandSetting;
import com.f.mvc.entity.server.BrandTunnelRelation;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.entity.server.TunnelSetting;
import com.f.mvc.service.pay.PayServerService;
import com.f.mvc.service.server.BrandSettingService;
import com.f.mvc.service.server.BrandTunnelRelationService;
import com.f.mvc.service.server.TunnelPayRelationService;
import com.f.mvc.service.server.TunnelSettingService;
import com.f.vo.BrandTunnelRelationVO;
import com.f.vo.ResponseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午4:27
 */
@RestController
@RequestMapping(value = "/brandTunnelRelation")
@Api(value = "/brandTunnelRelation", description = "品牌支付通道")
@Log4j2
public class BrandTunnelRelationAction extends BaseAction {


    private static final long serialVersionUID = 6181621559114981952L;

    @Autowired
    private BrandTunnelRelationService brandTunnelRelationService;

    @Autowired
    private TunnelPayRelationService tunnelPayRelationService;


    @Autowired
    private BrandSettingService brandSettingService;
    @Autowired
    private TunnelSettingService tunnelSettingService;
    @Autowired
    private PayServerService payServerService;

    @ApiOperation(value = "支付配置首页")
    @ApiParam(name = "type", value = "类型（传入值1）", required = true, type = "String")
    @PostMapping(value = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query(@RequestParam(value = "type") Integer type) {
        List<BrandSetting> brandSettingList = brandSettingService.findBrandSettingByType(type);
        return ResponseVo.builder().data(buildResult(brandSettingList)).build();
    }

    @ApiOperation(value = "查询某一个品牌支付配置详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand", value = "品牌brand", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "支付类型", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/queryDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo queryDetails(@RequestParam("brand") String brand, @RequestParam("type") String type) {
        BrandSetting brandSetting = brandSettingService.findBrandByBrandAndType(brand, type);
        return ResponseVo.builder().data(buildResult(Lists.newArrayList(brandSetting))).build();
    }

    /**
     * 修改权重
     *
     * @param tunnelPayId
     * @param weight
     * @return
     */
    @ApiOperation(value = "/weight", notes = "修改权重")
    @RequestMapping(value = "/weight", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo weight(
            @RequestParam(value = "brand") String brand,
            @RequestParam(value = "tunnelPayId") String tunnelPayId,
            @RequestParam(value = "weight") Integer weight) {
        BrandTunnelRelation brandTunnelRelation = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, tunnelPayId);
        if (brandTunnelRelation == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(tunnelPayId);
        if (tunnelPayRelation == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        brandTunnelRelation.setWeight(weight);
        int row = brandTunnelRelationService.updateWeightByTunnelPayId(brandTunnelRelation);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        payServerService.publish(tunnelPayId, tunnelPayRelation.getPayType(), brandTunnelRelation);
        return ResponseVo.builder().data(brandTunnelRelation).build();
    }


    /**
     * @param payType
     * @param state
     * @return
     */
    @ApiOperation(value = "品牌的开启和关闭")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand", value = "品牌", required = true, dataType = "String"),
            @ApiImplicitParam(name = "payType", value = "支付类型", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "state", value = "是否开启(true 开启 false关闭)", required = true, dataType = "boolean")
    })
    //todo  不要删除
    //@PostMapping(value = "/turnOperation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo turnOperation(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "payType") Integer payType,
                                    @RequestParam(value = "state") boolean state) {
        List<TunnelPayRelation> tunnelPayRelations = tunnelPayRelationService.findByPayType(payType);
        List<String> tunnelPayIds = Lists.newArrayList();
        tunnelPayRelations.stream().filter(Objects::nonNull).forEach(tunnelPayRelation -> {
            BrandTunnelRelation brandTunnelRelation = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, tunnelPayRelation.getTunnelPayId());
            if (brandTunnelRelation != null) {
                tunnelPayIds.add(brandTunnelRelation.getTunnelPayId());
            }
        });
        if (CollectionUtils.isNotEmpty(tunnelPayIds)) {
            int row = brandTunnelRelationService.turnOperation(tunnelPayIds, brand, state ? StateEnum.NORMAL.getCode() : StateEnum.DISABLED.getCode());
            if (row < 1) {
                return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        }
        tunnelPayRelations.stream().forEach(o -> {
            BrandTunnelRelation brandTunnelRelation = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, o.getTunnelPayId());
            payServerService.publish(o.getTunnelPayId(), o.getPayType(), brandTunnelRelation);
        });
        return ResponseVo.builder().build();
    }


    /**
     * @param brand
     * @param id
     * @param state
     * @return
     */
    @ApiOperation(value = "品牌的开启和关闭")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand", value = "品牌", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "关联id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "state", value = "是否开启(true 开启 false关闭)", required = true, dataType = "boolean")
    })
    @PostMapping(value = "/turnOperation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo turnOperation(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "id") String id,
                                    @RequestParam(value = "state") boolean state) {
        int row = brandTunnelRelationService.turnOperation(Lists.newArrayList(id), brand, state ? StateEnum.NORMAL.getCode() : StateEnum.DISABLED.getCode());
        if (row < 1) {
            ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        }
        brandTunnelRelationService.findByBrand(brand).stream().forEach(o -> {
            TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(o.getTunnelPayId());
            payServerService.publish(o.getTunnelPayId(), tunnelPayRelation.getPayType(), o);
        });

        return ResponseVo.builder().data(null).build();
    }

    /**
     * @param brand
     * @param tunnelPayId
     * @param weight
     * @param
     * @return
     */
    @ApiOperation(value = "/bind", notes = "品牌绑定支付类型")
    @RequestMapping(value = "/bind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo bind(@RequestParam(value = "brand") String brand,
                           @RequestParam(value = "tunnelPayId") String[] tunnelPayId,
                           @RequestParam(value = "weight") int[] weight) {
        if (tunnelPayId.length != weight.length) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("参数格式错误").build();
        }
        int count = brandSettingService.countByBrandAndType(brand, 1);
        if (count < 1) {
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("品牌信息不存在").build();
        }
        List<BrandTunnelRelation> list = Lists.newArrayList();
        AtomicInteger weightIndex = new AtomicInteger(0);
        Arrays.stream(tunnelPayId).forEach(o -> {
            TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(o);
            if (tunnelPayRelation != null) {
                BrandTunnelRelation oldBrandTunnelRelation = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, o);
                BrandTunnelRelation brandTunnelRelation = new BrandTunnelRelation();
                if (oldBrandTunnelRelation == null) {
                    brandTunnelRelation.setBrand(brand);
                    brandTunnelRelation.setTunnelPayId(o);
                    brandTunnelRelation.setWeight(weight[weightIndex.getAndIncrement()]);
                    List<TunnelPayRelation> tunnelPayRelations = tunnelPayRelationService.findByPayType(tunnelPayRelation.getPayType());
                    Optional<BrandTunnelRelation> btrl = tunnelPayRelations.stream().map(item -> {
                        BrandTunnelRelation btr = brandTunnelRelationService.findByBrandAndTunnelPayId(brand, item.getTunnelPayId());
                        return btr;
                    }).filter(Objects::nonNull).findFirst();
                    if (btrl.isPresent()) {
                        brandTunnelRelation.setState(btrl.get().getState());
                    } else {
                        brandTunnelRelation.setState(StateEnum.NORMAL.getCode());
                    }
                    list.add(brandTunnelRelation);
                }

            }
        });
        if (CollectionUtils.isNotEmpty(list)) {
            int row = brandTunnelRelationService.batchAddBrandTunnelRelation(list);
            if (row < 1) {
                return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            list.stream().forEach(o -> {
                for (String str : tunnelPayId) {
                    TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(o.getTunnelPayId());
                    payServerService.publish(str, tunnelPayRelation.getPayType(), o);
                }

            });
        }
        return ResponseVo.builder().code(HttpStatus.OK).build();
    }

    private List<BrandTunnelRelationVO> buildResult(List<BrandSetting> brandSettingList) {
        List<BrandTunnelRelationVO> result = Lists.newArrayList();
        Collection<BrandSetting> distinctBrandSettingList = brandSettingList.stream().filter(Objects::nonNull).collect(Collectors.toMap(o -> o.getBrand(), Function.identity(), (a, b) -> a, HashMap::new)).values();
        distinctBrandSettingList.stream().filter(Objects::nonNull).forEach(brandSetting -> {
            List<BrandTunnelRelation> tunnelRelationList = brandTunnelRelationService.findByBrand(brandSetting.getBrand());
            tunnelRelationList.stream().filter(Objects::nonNull).forEach(brandTunnelRelation -> {
                TunnelPayRelation tunnelPayRelation = tunnelPayRelationService.findByTunnelPayId(brandTunnelRelation.getTunnelPayId());
                TunnelSetting tunnelSetting = tunnelSettingService.findByTunnelId(tunnelPayRelation.getTunnelId());
                result.add(BrandTunnelRelationVO.builder()
                        .brandSetting(brandSetting)
                        .tunnelSetting(tunnelSetting)
                        .brandTunnelRelation(brandTunnelRelation)
                        .tunnelPayRelation(tunnelPayRelation)
                        .payTypeName(PayType.getMap().get(tunnelPayRelation.getPayType()))
                        .tunnelTypeName(TunnelType.getMap().get(tunnelSetting.getTunnelType()))
                        .build());
            });
        });
        result.sort(BrandTunnelRelationVO::compareByBrandThenPayType);
        return result;
    }
}
