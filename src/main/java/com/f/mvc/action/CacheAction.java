package com.f.mvc.action;


import com.f.base.BaseAction;
import com.f.vo.ResponseVo;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: bvsoo
 * Date: 2018/11/10
 * Time: 11:58 AM
 */
@RestController
@RequestMapping(value = "/cache")
@Api(value = "/cache", description = "缓存")
public class CacheAction extends BaseAction {

    private static final long serialVersionUID = 8961031258321009763L;
    @Resource
    private CacheManager cacheManager;

    /**
     * 查询缓存
     *
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "/query", notes = "查询缓存")
    public ResponseVo query() {
        if (cacheManager == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("未启用缓存！").build();
        return ResponseVo.builder().data(cacheManager.getCacheNames()).build();
    }


    /**
     * 清理缓存
     *
     * @param cacheName
     * @return
     */
    @RequestMapping(value = "/clear", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "/clear", notes = "清理缓存")
    public ResponseVo clear(@RequestParam(value = "cacheName", defaultValue = "*") String cacheName) {
        if (cacheManager == null)
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("未启用缓存！").build();
        if (Strings.isNullOrEmpty(cacheName))
            return ResponseVo.builder().code(HttpStatus.BAD_REQUEST).message("未启用缓存！").build();
        if (cacheName.startsWith("*")) {
            cacheManager.getCacheNames().stream().forEach(o -> {
                Cache cache = cacheManager.getCache(o);
                if (cache != null)
                    cache.clear();
            });
        } else {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null)
                cache.clear();
        }
        return ResponseVo.builder().build();
    }
}
