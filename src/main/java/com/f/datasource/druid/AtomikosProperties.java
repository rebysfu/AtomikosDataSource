package com.f.datasource.druid;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-30 上午10:03
 **/
@Getter
@Setter
public class AtomikosProperties {
    private int minPoolSize = 5;
    private int maxPoolSize = 200;
    private int borrowConnectionTimeout = 60;
    private int reapTimeout = 0;
    private int maxIdleTime = 60;
    private String testQuery;
    private int maintenanceInterval = 60;
    private int loginTimeout;
    private int defaultIsolationLevel = -1;
    private String xaDataSourceClassName;
    private int maxLifetime;
    private DruidDataSourceProperties xaProperties;
}
