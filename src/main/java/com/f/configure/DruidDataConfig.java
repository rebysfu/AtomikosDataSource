package com.f.configure;

import com.f.base.AbstractDataSourceConfig;
import com.f.constants.DataSourceKey;
import com.f.datasource.database.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-27 下午1:27
 **/
@Configuration
@PropertySource("classpath:mysql.properties")
public class DruidDataConfig extends AbstractDataSourceConfig{

    @Primary
    @Bean(name = DataSourceKey.MASTER)
    public DataSource dataSourceMaster(Environment environment) {
        return getDataSource(environment, "spring.druid.datasource.master.", DataSourceKey.MASTER);
    }

    @Bean(name = DataSourceKey.FISH2_INFO)
    public DataSource dataSourceInfo(Environment env) {
        return getDataSource(env, "spring.druid.datasource.fish2_info.", DataSourceKey.FISH2_INFO);
    }

    @Bean(name = DataSourceKey.FISH2_SERVER)
    public DataSource dataSourceServer(Environment env) {
        return getDataSource(env, "spring.druid.datasource.fish2_server.", DataSourceKey.FISH2_SERVER);
    }

    @Bean(name = DataSourceKey.FISH2_ORDER)
    public DataSource dataSourceOrder(Environment env) {
        return getDataSource(env, "spring.druid.datasource.fish2_order.", DataSourceKey.FISH2_ORDER);
    }

    @Bean(name = DataSourceKey.POKER_RECORD)
    public DataSource dataSourceRecord(Environment env) {
        return getDataSource(env, "spring.druid.datasource.poker_record.", DataSourceKey.POKER_RECORD);
    }

    @Bean(name = DataSourceKey.PROMOTION)
    public DataSource dataSourcePromotion(Environment env) {
        return getDataSource(env, "spring.druid.datasource.promotion.", DataSourceKey.PROMOTION);
    }

    @Bean(name = DataSourceKey.ANTISPAM)
    public DataSource dataSourceAntispam(Environment env) {
        return getDataSource(env, "spring.druid.datasource.antispam.", DataSourceKey.ANTISPAM);
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier(DataSourceKey.MASTER) DataSource dataSourceMaster,
                                               @Qualifier(DataSourceKey.FISH2_INFO) DataSource dataSourceInfo,
                                               @Qualifier(DataSourceKey.FISH2_SERVER) DataSource dataSourceServer,
                                               @Qualifier(DataSourceKey.FISH2_ORDER) DataSource dataSourceOrder,
                                               @Qualifier(DataSourceKey.POKER_RECORD) DataSource dataSourceRecord,
                                               @Qualifier(DataSourceKey.PROMOTION) DataSource dataSourcePromotion,
                                               @Qualifier(DataSourceKey.ANTISPAM) DataSource dataSourceAntispam) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceKey.MASTER, dataSourceMaster);
        targetDataSources.put(DataSourceKey.FISH2_INFO, dataSourceInfo);
        targetDataSources.put(DataSourceKey.FISH2_SERVER, dataSourceServer);
        targetDataSources.put(DataSourceKey.FISH2_ORDER, dataSourceOrder);
        targetDataSources.put(DataSourceKey.POKER_RECORD, dataSourceRecord);
        targetDataSources.put(DataSourceKey.PROMOTION, dataSourcePromotion);
        targetDataSources.put(DataSourceKey.ANTISPAM, dataSourceAntispam);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSourceMaster);
        return dataSource;
    }

}
