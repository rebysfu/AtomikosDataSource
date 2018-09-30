package com.f.datasource.api.config;

import java.util.Collection;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 上午11:36
 **/
public interface DynamicDataSourceConfigRepository<C extends DynamicDataSourceConfig> {
    Collection<C> findAll();

    C findById(String dataSourceId);

    C add(C config);

    C remove(String dataSourceId);
}