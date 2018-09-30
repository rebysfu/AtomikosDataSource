package com.f.datasource.jta;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.f.datasource.api.config.DynamicDataSourceConfig;
import com.f.datasource.druid.DruidDataSourceProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:36
 **/
@EqualsAndHashCode(callSuper = true)
@Log4j2
@Data
public class AtomikosDataSourceConfig extends DynamicDataSourceConfig {
    private static final long serialVersionUID = 5588085000663972571L;
    private int minPoolSize = 10;
    private int maxPoolSize = 50;
    private int borrowConnectionTimeout = 600;
    private int reapTimeout;
    private int maxIdleTime = 60;
    private int maintenanceInterval = 60;
    private int defaultIsolationLevel = -1;
    private String xaDataSourceClassName;
    private int loginTimeout=30;
    private String testQuery;
    private int maxLifetime=20000;
    private DruidDataSourceProperties xaProperties;
    //初始化超时时间
    private int initTimeout = 300;

    public void putProperties(AtomikosDataSourceBean atomikosDataSourceBean) {
        //定义一个实体接受druid 链接池对象，然后构建一个properties 注入进去
        atomikosDataSourceBean.setXaDataSourceClassName(getXaDataSourceClassName());
        atomikosDataSourceBean.setBorrowConnectionTimeout(getBorrowConnectionTimeout());
        if (loginTimeout != 0) {
            try {
                atomikosDataSourceBean.setLoginTimeout(getLoginTimeout());
            } catch (SQLException e) {
                log.warn(e.getMessage(), e);
            }
        }

        atomikosDataSourceBean.setMaxIdleTime(getMaxIdleTime());
        atomikosDataSourceBean.setMaxPoolSize(getMaxPoolSize());
        atomikosDataSourceBean.setMinPoolSize(getMinPoolSize());
        atomikosDataSourceBean.setDefaultIsolationLevel(getDefaultIsolationLevel());
        atomikosDataSourceBean.setMaintenanceInterval(getMaintenanceInterval());
        atomikosDataSourceBean.setReapTimeout(getReapTimeout());
        atomikosDataSourceBean.setTestQuery(getTestQuery());
        atomikosDataSourceBean.setXaProperties(build(xaProperties));
        atomikosDataSourceBean.setMaxLifetime(getMaxLifetime());
    }

}
