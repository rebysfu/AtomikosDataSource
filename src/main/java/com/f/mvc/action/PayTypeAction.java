package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.enums.PayType;
import com.f.mvc.entity.server.TunnelPayRelation;
import com.f.mvc.service.server.TunnelPayRelationService;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * User: bvsoo
 * Date: 2018/10/10
 * Time: 下午3:24
 */
@RestController
@RequestMapping(value = "/payType")
@Api(value = "/payType", description = "支付类型")
@Log4j2
public class PayTypeAction extends BaseAction {
    private static final long serialVersionUID = -2104477885794954050L;
    @Autowired
    private TunnelPayRelationService tunnelPayRelationService;

    /**
     * 查询支付类型
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询支付类型")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        return ResponseVo.builder().data(PayType.getMapList()).build();
    }

    /**
     * 查询支付类型
     *
     * @return
     */
    @ApiOperation(value = "查询通道类型,剔除已绑定的支付类型")
    @RequestMapping(value = "/queryPayTypeByTunnelId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo queryPayType(@RequestParam("tunnelId") String tunnelId) {
        List<TunnelPayRelation> tunnelPayRelations = tunnelPayRelationService.findByTunnelId(tunnelId);
        List<Map<String, Object>> payTypes = Lists.newArrayList();
        PayType.getMapList().stream().forEach(item -> {
            payTypes.add(item);
            tunnelPayRelations.stream().filter(Objects::nonNull).forEach(tunnelPayRelation -> {
                if (Objects.equals(item.get("value"), tunnelPayRelation.getPayType())) {
                    payTypes.remove(item);
                }
            });
        });
        return ResponseVo.builder().data(payTypes).build();
    }
}
