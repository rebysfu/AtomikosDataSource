package com.f.base;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-27 下午1:40
 **/
@Log4j2
public abstract class AbstractDataSourceConfig {
    private static final int minPoolSize = 10;
    private static final int maxPoolSize = 50;
    private static final int borrowConnectionTimeout = 60;
    private static final int defaultIsolationLevel = -1;
    private final static String xaDataSourceClassName = "com.alibaba.druid.pool.xa.DruidXADataSource";
    private String testQuery = "SELECT 1";

    protected DataSource getDataSource(Environment env, String prefix, String dataSourceName){
        Properties properties = build(env,prefix);
        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setUniqueResourceName(dataSourceName);
        atomikosDataSource.setXaProperties(properties);
        atomikosDataSource.setXaDataSourceClassName(xaDataSourceClassName);
        atomikosDataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxActive", String.valueOf(maxPoolSize))));
        atomikosDataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minIdle", String.valueOf(minPoolSize))));
        atomikosDataSource.setTestQuery(testQuery);
        atomikosDataSource.setMaxLifetime(1000);
        atomikosDataSource.setBorrowConnectionTimeout(borrowConnectionTimeout);
        atomikosDataSource.setDefaultIsolationLevel(defaultIsolationLevel);
        return atomikosDataSource;
    }

    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "userName"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", String.class));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("timeBetweenEvictionRunsMillis", env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));
        prop.put("connectionProperties", env.getProperty(prefix + "connectionProperties", String.class));
        return prop;
    }
}
