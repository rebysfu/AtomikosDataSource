package com.f.datasource.aspect;

import com.f.datasource.DataSourceContextHolder;
import com.f.datasource.annotations.DataSource;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author rebysfu@gmail.com
 * @description： 数据源切换注解
 * @create 2018-09-27 下午1:27
 **/
@Aspect
@Component
@Order(1)
@Log4j2
public class DataSourceAspect {

    /**
     * @param clazz
     * @param name
     * @return
     */
    private static Method findUniqueMethod(Class<?> clazz, String name) {
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
            for (Method method : methods) {
                if (name.equals(method.getName())) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }

    @Pointcut(("@within(com.f.datasource.annotations.DataSource)||@annotation(com.f.datasource.annotations.DataSource)"))
    protected void datasourceAspect() {

    }

    /**
     * 根据@DataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
     */
    @Before("datasourceAspect()")
    public void changeDataSourceBeforeMethodExecution(JoinPoint jp) {
        DataSourceContextHolder.setDataSourceKey(determineDatasource(jp));
    }

    /**
     * 设置为上次使用的数据源
     */
    @After("datasourceAspect()")
    public void restoreDataSourceAfterMethodExecution() {
        DataSourceContextHolder.clearDataSourceKey();
    }

    /**
     * @param jp
     * @return
     */
    public String determineDatasource(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class targetClass = jp.getSignature().getDeclaringType();
        String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
        String dataSourceForTargetMethod = resolveDataSourceFromMethod(targetClass, methodName);
        String dataSourceKey = determinateDataSource(dataSourceForTargetClass, dataSourceForTargetMethod);
        return dataSourceKey;
    }

    /**
     * @param targetClass
     * @param methodName
     * @return
     */
    private String resolveDataSourceFromMethod(Class targetClass, String methodName) {
        Method m = findUniqueMethod(targetClass, methodName);
        if (m != null) {
            DataSource choDs = m.getAnnotation(DataSource.class);
            return resolveDataSourceName(choDs);
        }
        return null;
    }

    /**
     * @param classDS
     * @param methodDS
     * @return
     */
    private String determinateDataSource(String classDS, String methodDS) {
        return methodDS == null ? classDS : methodDS;
    }

    /**
     * @param targetClass
     * @return
     */
    private String resolveDataSourceFromClass(Class targetClass) {
        DataSource classAnnotation = (DataSource) targetClass.getAnnotation(DataSource.class);
        return null != classAnnotation ? resolveDataSourceName(classAnnotation) : null;
    }

    /**
     * @param ds
     * @return
     */
    private String resolveDataSourceName(DataSource ds) {
        return ds == null ? null : ds.value().getName();
    }
}
