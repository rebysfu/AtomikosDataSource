package com.f.datasource.aspect;

import com.f.datasource.annotations.Redis;
import com.f.datasource.core.DynamicRedisTemplate;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * User: bvsoo
 * Date: 2018/10/24
 * Time: 11:56 AM
 */
@Aspect
@Component
@Order(1)
@Log4j2
public class RedisTemplateAspect {

    @Pointcut(("@within(com.f.datasource.annotations.Redis)||@annotation(com.f.datasource.annotations.Redis)"))
    protected void redisAspect() {
    }


    @After(value = "redisAspect()")
    public void afterRedisAspect(JoinPoint joinPoint) {
        DynamicRedisTemplate.remove();
    }

    @Before(value = "redisAspect()")
    public void beforeRedisAspect(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Class clazz = targetMethod.getClass();
        Redis clazzRedis = null;
        if (clazz.isAnnotationPresent(Redis.class))
            clazzRedis = (Redis) clazz.getAnnotation(Redis.class);
        Redis methodRedis = targetMethod.getAnnotation(Redis.class);
        if (methodRedis != null) {
            DynamicRedisTemplate.setCurrentDbIndex(Pair.of(methodRedis.value().ordinal(), methodRedis.dbIndex()));
        } else {
            if (clazzRedis != null)
                DynamicRedisTemplate.setCurrentDbIndex(Pair.of(methodRedis.value().ordinal(), methodRedis.dbIndex()));
        }
    }
}
