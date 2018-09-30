package com.f.datasource.api;

import com.f.datasource.druid.AtomikosProperties;
import com.f.datasource.druid.DruidDataSourceProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-29 下午9:58
 **/
@Log4j2
public abstract class AbstractDataSourceConfiguration {

    protected DataSource getDataSource(Environment env, String prefix, String dataSourceName) {
        Properties prop = build(env, prefix);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        return ds;
    }
   /* protected DataSource getDataSource(AtomikosProperties atomikosProperties, String dataSourceName) {
        Properties prop = build(atomikosProperties.getXaProperties());
        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSourceClassName(atomikosProperties.getXaDataSourceClassName());
        atomikosDataSource.setUniqueResourceName(dataSourceName);
        atomikosDataSource.setXaProperties(prop);
        atomikosDataSource.setBorrowConnectionTimeout(atomikosProperties.getBorrowConnectionTimeout());
        atomikosDataSource.setDefaultIsolationLevel(atomikosProperties.getDefaultIsolationLevel());
        if (atomikosProperties.getLoginTimeout() != 0) {
            try {
                atomikosDataSource.setLoginTimeout(atomikosProperties.getLoginTimeout());
            } catch (SQLException e) {
                log.warn(e.getMessage(), e);
            }
        }
        atomikosDataSource.setMaintenanceInterval(atomikosProperties.getMaintenanceInterval());
        atomikosDataSource.setMaxIdleTime(atomikosProperties.getMaxIdleTime());
        atomikosDataSource.setMaxLifetime(atomikosProperties.getMaxLifetime());
        atomikosDataSource.setMaxPoolSize(atomikosProperties.getMaxPoolSize());
        atomikosDataSource.setMinPoolSize(atomikosProperties.getMinPoolSize());
        atomikosDataSource.setReapTimeout(atomikosProperties.getReapTimeout());

        return atomikosDataSource;
    }*/

    protected Properties build(DruidDataSourceProperties xaProperties) {
        Properties properties = new Properties();
        properties.put("url", xaProperties.getUrl());
        properties.put("username", xaProperties.getUsername());
        properties.put("password", xaProperties.getPassword());
        //properties.put("driverClassName", xaProperties.getDriverClassName());
        properties.put("initialSize", xaProperties.getInitialSize());
        properties.put("maxActive", xaProperties.getMaxActive());
        properties.put("minIdle", xaProperties.getMinIdle());
        properties.put("maxWait", xaProperties.getMaxWait());
        properties.put("poolPreparedStatements", xaProperties.isPoolPreparedStatements());
        properties.put("maxPoolPreparedStatementPerConnectionSize", xaProperties.getMaxPoolPreparedStatementPerConnectionSize());
        properties.put("validationQuery", xaProperties.getValidationQuery());
        properties.put("testOnBorrow", xaProperties.isTestOnBorrow());
        properties.put("testOnReturn", xaProperties.isTestOnReturn());
        properties.put("testWhileIdle", xaProperties.isTestWhileIdle());
        properties.put("timeBetweenEvictionRunsMillis", xaProperties.getTimeBetweenEvictionRunsMillis());
        properties.put("minEvictableIdleTimeMillis", xaProperties.getMinEvictableIdleTimeMillis());
        properties.put("filters", xaProperties.getFilters());
        properties.put("connectionProperties", xaProperties.getConnectionProperties());
        return properties;
    }

   /**
     * 主要针对druid数据库链接池
     *
     * @param env
     * @param prefix
     * @return
     */
    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        //prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        return prop;
    }
}
