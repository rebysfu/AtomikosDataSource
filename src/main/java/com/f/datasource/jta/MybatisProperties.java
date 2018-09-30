package com.f.datasource.jta;

import com.f.datasource.api.DataSourceHolder;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-23 下午12:15
 **/
public class MybatisProperties extends org.mybatis.spring.boot.autoconfigure.MybatisProperties {

    /**
     * 是否启用动态数据源
     * 启用后调用{@link DataSourceHolder#switcher()},mybatis也会进行数据源切换
     *
     * @see DataSourceHolder#switcher()
     */
    private boolean dynamicDatasource = true;

    public boolean isDynamicDatasource() {
        return dynamicDatasource;
    }

    public void setDynamicDatasource(boolean dynamicDatasource) {
        this.dynamicDatasource = dynamicDatasource;
    }

}
