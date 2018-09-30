package com.f.datasource.api;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author rebysfu@gmail.com
 * @description：数据库类型枚举,方便后续其他数据库的扩展
 * @create 2018-09-20 上午11:55
 **/
public enum DatabaseType {
    unknown(null, null, null, String::isEmpty),
    mysql("com.mysql.jdbc.Driver", "com.alibaba.druid.pool.DruidDataSource", "select 1", createUrlPredicate("mysql"));

    private final String testQuery;
    private final String driverClassName;
    private final String xaDataSourceClassName;
    private final Predicate<String> urlPredicate;

    DatabaseType(String driverClassName, String xaDataSourceClassName, String testQuery, Predicate<String> urlPredicate) {
        this.driverClassName = driverClassName;
        this.testQuery = testQuery;
        this.xaDataSourceClassName = xaDataSourceClassName;
        this.urlPredicate = urlPredicate;
    }

    static Predicate<String> createUrlPredicate(String name) {
        return url -> {
            String urlWithoutPrefix = url.substring("jdbc".length()).toLowerCase();
            String prefix = ":" + name.toLowerCase() + ":";
            return urlWithoutPrefix.startsWith(prefix);
        };
    }

    public static DatabaseType fromJdbcUrl(String url) {
        if (Objects.nonNull(url)) {
            return Arrays.stream(values()).filter(type -> type.urlPredicate.test(url)).findFirst().orElse(unknown);
        }
        return unknown;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getXaDataSourceClassName() {
        return xaDataSourceClassName;
    }

    public String getTestQuery() {
        return testQuery;
    }
}
