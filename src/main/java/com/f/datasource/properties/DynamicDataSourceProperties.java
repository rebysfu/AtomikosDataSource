package com.f.datasource.properties;

import com.f.datasource.configure.DynamicDatasourceConfig;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-10-05 上午11:27
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.druid")
@PropertySource(value = {"classpath:mysql.properties"}, encoding = "UTF-8")
public class DynamicDataSourceProperties {
    @Getter
    @Setter
    private Map<String, DynamicDatasourceConfig> datasource = Maps.newHashMap();
}
