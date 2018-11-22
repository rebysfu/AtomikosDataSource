package com.f.datasource.annotations;


import com.f.enums.RedisKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: bvsoo
 * Date: 2018/10/24
 * Time: 11:56 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Redis {
    RedisKey value() default RedisKey.MASTER;

    int dbIndex() default 0;
}
