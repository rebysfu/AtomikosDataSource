package com.f.datasource.configure;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rebysfu@gmail.com
 * @description： 动态数据源配置
 * @create 2018-10-05 上午11:31
 **/
@Getter
@Setter
public class DynamicDatasourceConfig {
    private boolean primary;
    private String url;
    private String username;
    private String password;
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    private int initialSize = 10;
    private int minIdle = 1;
    private int maxActive = 8;
    private int maxWait;
    private boolean useUnfairLock = true;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery = "SELECT 1";
    private boolean testWhileIdle;
    private boolean testOnBorrow = true;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize = 100;
    private String filters;
    private String connectionProperties;

}
