package com.f.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * User: bvsoo
 * Date: 2018/10/24
 * Time: 12:08 PM
 */
public enum RedisKey {
    MASTER("master"),
    SLAVE("slave"),
    OFFLINE("offline"),
    PROMOTION("promotion"),
    ;
    private static Map<Integer, RedisKey> IDX_MAP = Maps.newHashMap();
    private static Map<String, RedisKey> NAME_MAP = Maps.newHashMap();

    static {
        for (RedisKey redisKey : values()) {
            IDX_MAP.put(redisKey.ordinal(), redisKey);
            NAME_MAP.put(redisKey.name, redisKey);
        }
    }

    private final String name;

    RedisKey(String name) {
        this.name = name;
    }

    public static RedisKey fromIndex(int idx) {
        return IDX_MAP.get(idx);
    }

    public static RedisKey fromName(String name) {
        return NAME_MAP.get(name);
    }

    public String getName() {
        return name;
    }

}
