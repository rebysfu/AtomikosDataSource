package com.f.datasource.api.service;

import com.f.datasource.api.DynamicDataSource;
import com.f.datasource.api.DynamicDataSourceProxy;
import com.f.datasource.api.config.DynamicDataSourceConfigRepository;
import com.f.datasource.api.config.InSpringDynamicDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author rebysfu@gmail.com
 * @description：基于spring容器的动态数据源服务。从spring容器中获取数据源
 * @create 2018-09-20 上午11:38
 **/
public class InSpringContextDynamicDataSourceService extends AbstractDynamicDataSourceService<InSpringDynamicDataSourceConfig> {
    private ApplicationContext applicationContext;

    public InSpringContextDynamicDataSourceService(DynamicDataSourceConfigRepository<InSpringDynamicDataSourceConfig> repository, DynamicDataSource defaultDataSource) {
        super(repository, defaultDataSource);
    }

    public InSpringContextDynamicDataSourceService(DynamicDataSourceConfigRepository<InSpringDynamicDataSourceConfig> repository, DataSource dataSource) throws SQLException {
        super(repository, dataSource);
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected DataSourceCache createCache(InSpringDynamicDataSourceConfig config) {
        DataSource dataSource = applicationContext.getBean(config.getBeanName(), DataSource.class);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            return new DataSourceCache(config.hashCode(),
                    new DynamicDataSourceProxy(config.getId(), dataSource),
                    countDownLatch,
                    config);
        } finally {
            countDownLatch.countDown();
        }
    }
}
