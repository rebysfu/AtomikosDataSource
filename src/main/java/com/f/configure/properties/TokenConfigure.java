package com.f.configure.properties;

import com.google.common.base.Strings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/14
 * Time: 下午7:01
 */
@Data
@ConfigurationProperties(prefix = "spring.token")
@Component
public class TokenConfigure implements Serializable {
    public static final String DEFAULT_ALGORITHM = "HS512";
    public static final String DEFAULT_SECRET = "WM5MjI0Y";
    public static final long DEFAULT_TIMEOUT = 600L;
    private static final long serialVersionUID = -7317352483097899554L;
    private String algorithm;
    private String secret;
    private Long timeout;


    public String getAlgorithm() {
        return Strings.isNullOrEmpty(algorithm) ? DEFAULT_ALGORITHM : algorithm;
    }

    public String getSecret() {
        return Strings.isNullOrEmpty(secret) ? DEFAULT_SECRET : secret;
    }

    public Long getTimeout() {
        return (timeout == null || timeout.longValue() < 1) ? DEFAULT_TIMEOUT : timeout;
    }
}
