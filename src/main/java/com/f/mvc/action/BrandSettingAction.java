package com.f.mvc.action;

import com.f.base.BaseAction;
import com.f.mvc.entity.server.BrandSetting;
import com.f.mvc.service.server.BrandSettingService;
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

import java.util.List;

/**
 * User: bvsoo
 * Date: 2018/9/25
 * Time: 下午4:39
 */
@RestController
@RequestMapping(value = "/brandSetting")
@Api(value = "/brandSetting", description = "品牌")
@Log4j2
public class BrandSettingAction extends BaseAction {
    private static final long serialVersionUID = -2961744427355052690L;

    @Autowired
    private BrandSettingService brandSettingService;

    /**
     * 查询全部品牌
     *
     * @return
     */
    @ApiOperation(value = "/query", notes = "查询全部品牌")
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo query() {
        List<BrandSetting> brandSettings = brandSettingService.findBrandSettingByType(1);
        return ResponseVo.builder().data(brandSettings).build();
    }


    /**
     * 查询所有品牌
     *
     * @return
     */
    @ApiOperation(value = "/generate", notes = "添加品牌")
    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo generate(@RequestParam(value = "brand") String brand,
                               @RequestParam(value = "name") String name) {
        if (Strings.isNullOrEmpty(brand) || Strings.isNullOrEmpty(name))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        long count = brandSettingService.countByBrandAndType(brand, 1);
        if (count > 0)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).build();
        BrandSetting brandSetting = new BrandSetting();
        brandSetting.setBrand(brand);
        brandSetting.setName(name);
        brandSetting.setType(1);
        int row = brandSettingService.addNewBrandSetting(brandSetting);
        if (row < 1)
            return ResponseVo.builder().code(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseVo.builder().data(brandSetting).build();
    }

    /**
     * 查询品牌支付配置
     *
     * @return
     */
    @ApiOperation(value = "/pay", notes = "查询品牌支付配置")
    @RequestMapping(value = "/pay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVo pay() {
        List<BrandSetting> brandSettings = brandSettingService.findBrandSettingByType(1);
        return ResponseVo.builder().data(brandSettings).build();
    }


}
