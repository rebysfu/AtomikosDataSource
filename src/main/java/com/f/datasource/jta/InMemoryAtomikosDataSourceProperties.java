package com.f.datasource.jta;

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
 * @create 2018-09-24 下午5:01
 **/
@ConfigurationProperties(prefix = "dynamic.datasource")
@PropertySource(value = {"classpath:mysql.properties"}, encoding = "UTF-8")
@Configuration
public class InMemoryAtomikosDataSourceProperties {
    @Getter
    @Setter
    private Map<String, AtomikosDataSourceConfig> jta = Maps.newHashMap();

}
