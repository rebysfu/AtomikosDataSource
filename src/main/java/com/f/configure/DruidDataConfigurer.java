package com.f.configure;

import com.f.datasource.database.DynamicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-27 下午1:27
 **/
@Configuration
public class DruidDataConfigurer {
    @Primary
    @Bean
    public DynamicDataSource dynamicDataSource() {
        return new DynamicDataSource();
    }
}
