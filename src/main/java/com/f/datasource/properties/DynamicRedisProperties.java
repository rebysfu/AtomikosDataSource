package com.f.datasource.properties;

import com.f.datasource.configure.DynamicRedisConfig;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * User: bvsoo
 * Date: 2018/10/22
 * Time: 3:46 PM
 */
@Configuration
@ConfigurationProperties(prefix = "spring")
@PropertySource(value = {"classpath:redis.properties"}, encoding = "UTF-8")
@Data
@ToString
public class DynamicRedisProperties {
    private Map<String, DynamicRedisConfig> redis = Maps.newHashMap();
}
