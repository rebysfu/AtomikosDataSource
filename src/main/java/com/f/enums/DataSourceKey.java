package com.f.enums;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * 数据源常量,此常量必须和配置属性文件中配置的数据源一致，不然会启动报错
 */
@AllArgsConstructor()
public enum DataSourceKey {
    AUTH("auth"),
    FISH2_INFO("fish2_info"),
    FISH2_SERVER("fish2_server"),
    FISH2_ORDER("fish2_order"),
    POKER_RECORD("poker_record"),
    PROMOTION("promotion"),
    ANTISPAM("antispam");
    private static Map<String, DataSourceKey> Cache = Maps.newHashMap();

    static {
        for (DataSourceKey dataSourceKey : DataSourceKey.values()) {
            Cache.put(dataSourceKey.name, dataSourceKey);
        }
    }

    @Getter
    private final String name;

    public static boolean isNotDefined(String name) {
        return Cache.get(name) == null;
    }
}
