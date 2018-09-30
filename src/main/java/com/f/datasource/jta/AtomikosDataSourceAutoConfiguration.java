package com.f.datasource.jta;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosSQLException;
import com.f.datasource.api.DynamicDataSourceAutoConfiguration;
import com.f.datasource.api.DynamicDataSourceService;
import com.f.datasource.api.config.DynamicDataSourceConfigRepository;
import com.f.datasource.druid.DruidDataSourceProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:38
 **/
@Configuration
@AutoConfigureBefore(DynamicDataSourceAutoConfiguration.class)
@PropertySource(value = {"classpath:mysql.properties"}, encoding = "UTF-8")
public class AtomikosDataSourceAutoConfiguration {
    //默认数据源
    @Bean(initMethod ="init",destroyMethod = "destroy", value = "defaultDataSource")
    @Primary
    @ConfigurationProperties(prefix = "dynamic.datasource.jta.master")
    public AtomikosDataSourceBean defaultDataSource() {
        return new AtomikosDataSourceBean();
    }

    @ConditionalOnMissingBean(JtaDataSourceRepository.class)
    @Bean
    public InMemoryAtomikosDataSourceRepository memoryJtaDataSourceStore() {
        return new InMemoryAtomikosDataSourceRepository();
    }

    @Bean
    @Primary
    public DynamicDataSourceService jtaDynamicDataSourceService(DynamicDataSourceConfigRepository<AtomikosDataSourceConfig> repository
            , DataSource dataSource) throws SQLException {
        return new JtaDynamicDataSourceService(repository, dataSource);
    }
}
