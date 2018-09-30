package com.f.datasource.api.strategy;

import com.f.datasource.api.annotation.DataSource;
import com.f.datasource.api.annotation.UseDefaultDataSource;
import com.f.datasource.api.utils.AopUtils;

import java.lang.reflect.Method;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:00
 **/
public class AnnotationDataSourceSwitchStrategyMatcher extends CachedDataSourceSwitchStrategyMatcher {
    /**
     * 模版方法
     */
    @Override
    public Strategy createStrategyIfMatch(Class target, Method method) {
        DataSource useDataSource = AopUtils.findAnnotation(target, method, DataSource.class);
        UseDefaultDataSource useDefaultDataSource = AopUtils.findAnnotation(target, method, UseDefaultDataSource.class);

        boolean support = useDataSource != null || useDefaultDataSource != null;
        if (support) {
            return new Strategy() {
                @Override
                public boolean isUseDefaultDataSource() {
                    return useDefaultDataSource != null;
                }

                @Override
                public boolean isFallbackDefault() {
                    return useDataSource != null && useDataSource.fallbackDefault();
                }

                @Override
                public String getDataSourceId() {
                    return useDataSource == null ? null : useDataSource.value();
                }

                @Override
                public String toString() {
                    return "Annotation Strategy(" + (useDataSource != null ? useDataSource : useDefaultDataSource) + ")";
                }
            };
        }
        return null;
    }
}
