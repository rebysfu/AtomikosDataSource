package com.f.helper;

import com.f.base.Base64;
import com.f.configure.properties.TokenConfigure;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * User: bvsoo
 * Date: 2018/9/14
 * Time: 下午7:26
 */
public final class TokenHelper {

    /**
     * @param timeout
     * @return
     */
    public static Date expirationDate(Long timeout) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (timeout != null) {
            return Date.from(localDateTime.plusSeconds(timeout).atZone(ZoneId.systemDefault()).toInstant());
        } else {
            return Date.from(localDateTime.plusSeconds(TokenConfigure.DEFAULT_TIMEOUT).atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    /**
     * @param secret
     * @return
     */
    public static String getSecret(String secret) throws UnsupportedEncodingException {
        return Base64.encode(secret.getBytes(StandardCharsets.UTF_8.name()));
    }

}
