package com.f.datasource.annotations;


import com.f.enums.DataSourceKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rebysfu@gmail.com
 * @description： 数据源切换注解
 * @create 2018-09-27 下午1:27
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSource {
    DataSourceKey value();
}
