package com.f.base;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;

/**
 * User: bvsoo
 * Date: 2018/9/13
 * Time: 上午11:16
 */
public class BaseAction implements Serializable {
    private static final long serialVersionUID = -4930243806625410350L;
    /**
     * lock
     */
    private static final byte[] lock = new byte[]{98, 118, 115, 111, 111, 95, 108, 111, 99, 107, 95};
    /**
     * 锁前缀
     */
    private static final String LOCK_PREFIX = new String(lock, StandardCharsets.UTF_8);
    /**
     * 多键分割符
     */
    private static final String SEPARATOR = "_";

    /**
     * 获取当前请求中的用户信息
     *
     * @param request
     * @return
     */
    public Long getUserId(HttpServletRequest request) {
        return (Long) request.getAttribute(Auth.USER_ID_KEY);
    }


    /**
     * 构建synchronized mutex
     *
     * @param values
     * @return
     */
    public String generateMutex(String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LOCK_PREFIX);
        if (values != null || values.length > 0) {
            Iterator<String> iterator = Arrays.asList(values).iterator();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next());
                if (iterator.hasNext()) stringBuilder.append(SEPARATOR);
            }
        }
        return stringBuilder.toString().intern();
    }
}
