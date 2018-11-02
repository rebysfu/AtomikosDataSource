package com.f.datasource.configure;

import lombok.Data;

/**
 * User: bvsoo
 * Date: 2018/10/24
 * Time: 1:04 PM
 */
@Data
public class DynamicRedisConfig {
    private String host;
    private Integer port = 6379;
    private String password;
    private Integer timeout;
    private Integer maxIdle;
    private Integer maxActive;
    private Integer maxWait;
    private boolean testOnBorrow;
}
