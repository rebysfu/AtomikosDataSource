package com.f.datasource.api;

import com.f.datasource.api.switcher.DataSourceSwitcher;

import javax.sql.DataSource;

/**
 * @author rebysfu@gmail.com
 * @description：动态数据源
 * @create 2018-09-20 上午11:41
 **/
public interface DynamicDataSource {
    /**
     * @return 数据源ID
     * @see DataSourceSwitcher#currentDataSourceId()
     */
    String getId();

    /**
     * @return 数据库类型
     * @see DatabaseType
     */
    DatabaseType getType();

    /**
     * @return 原始数据源
     */
    DataSource getNative();
}
