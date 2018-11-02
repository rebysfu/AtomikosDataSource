package com.f.helper;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;

/**
 * User: bvsoo
 * Date: 2018/10/27
 * Time: 5:02 PM
 */
@Log4j2
public final class JSONHelper {
    /**
     * 校验json字符串是否合法
     *
     * @param json
     * @return
     */
    public static boolean verifyString(String json) {
        try {
            JSON.parse(json);
            return true;
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        return false;
    }

    /**
     * 转换成标准JSON字符串
     *
     * @param json
     * @return
     */
    public static String toJSONString(String json) {
        if (!verifyString(json)) return null;
        Object obj = JSON.parse(json);
        return obj == null ? null : JSON.toJSONString(obj);
    }
}
