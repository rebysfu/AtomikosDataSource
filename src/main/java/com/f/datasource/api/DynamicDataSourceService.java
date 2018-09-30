package com.f.datasource.api;

import com.f.datasource.api.exception.DataSourceNotFoundException;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 上午11:40
 **/
public interface DynamicDataSourceService {
    /**
     * 根据数据源ID获取动态数据源,数据源不存在将抛出{@link DataSourceNotFoundException}
     *
     * @param dataSourceId 数据源ID
     * @return 动态数据源
     */
    DynamicDataSource getDataSource(String dataSourceId);

    /**
     * @return 默认数据源
     */
    DynamicDataSource getDefaultDataSource();
}
