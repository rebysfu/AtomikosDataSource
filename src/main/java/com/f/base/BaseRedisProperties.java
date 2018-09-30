package com.f.base;

import lombok.Data;

import java.io.Serializable;

/**
 * User: bvsoo
 * Date: 2018/9/20
 * Time: 上午10:49
 */
@Data
public class BaseRedisProperties implements Serializable {
    private static final long serialVersionUID = 8125263791137663694L;
    private long timeout;
    private long maxIdle;
    private long maxActive;
    private long maxWait;
    private boolean testOnBorrow;
}
