package com.f.datasource.api.annotation;

import com.f.datasource.api.DataSourceHolder;
import com.f.datasource.api.DynamicDataSource;
import com.f.datasource.api.exception.DataSourceNotFoundException;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface DataSource {
    /**
     * @return 数据源ID ,支持表达式如 : ${#param.id}
     * @see DynamicDataSource#getId()
     */
    String value();

    /**
     * @return 数据源不存在时, 是否使用默认数据源.
     * 如果为{@code false},当数据源不存在的时候,
     * 将抛出 {@link DataSourceNotFoundException}
     * @see DataSourceHolder#currentExisting()
     */
    boolean fallbackDefault() default false;
}
