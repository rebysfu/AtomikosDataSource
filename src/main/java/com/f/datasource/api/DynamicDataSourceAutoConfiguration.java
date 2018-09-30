package com.f.datasource.api;

import com.f.datasource.api.config.DynamicDataSourceConfigRepository;
import com.f.datasource.api.config.InSpringDynamicDataSourceConfig;
import com.f.datasource.api.service.InSpringContextDynamicDataSourceService;
import com.f.datasource.api.service.InSpringDynamicDataSourceConfigRepository;
import com.f.datasource.api.switcher.DataSourceSwitcher;
import com.f.datasource.jta.JtaDynamicDataSourceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:47
 **/
@Configuration
@ImportAutoConfiguration(AopDataSourceSwitcherAutoConfiguration.class)
public class DynamicDataSourceAutoConfiguration implements BeanPostProcessor {
    @Bean
    @ConditionalOnMissingBean(DynamicDataSourceConfigRepository.class)
    public InSpringDynamicDataSourceConfigRepository inSpringDynamicDataSourceConfigRepository() {
        return new InSpringDynamicDataSourceConfigRepository();
    }

    @Bean
    @ConditionalOnMissingBean(DynamicDataSourceService.class)
    public InSpringContextDynamicDataSourceService inMemoryDynamicDataSourceService(DynamicDataSourceConfigRepository<InSpringDynamicDataSourceConfig> repository,
                                                                                    DataSource dataSource) {
        DynamicDataSourceProxy dataSourceProxy = new DynamicDataSourceProxy(null, dataSource);
        return new InSpringContextDynamicDataSourceService(repository, dataSourceProxy);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof JtaDynamicDataSourceService) {
            DataSourceHolder.dynamicDataSourceService = ((JtaDynamicDataSourceService) bean);
        }
        if (bean instanceof DataSourceSwitcher) {
            DataSourceHolder.dataSourceSwitcher = ((DataSourceSwitcher) bean);
        }
        return bean;
    }


    @Configuration
    public static class AutoRegisterDataSource {
        @Autowired
        public void setDataSourceService(DynamicDataSourceService dataSourceService) {
            DataSourceHolder.dynamicDataSourceService = dataSourceService;
        }
    }

}
