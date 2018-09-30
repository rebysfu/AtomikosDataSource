package com.f.datasource.aspect;


import com.f.datasource.annotations.DataSource;
import com.f.datasource.database.DataSourceContextHolder;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(-10)
@Log4j2
public class DataSourceAspect {
    protected static final ThreadLocal<String> preDatasourceHolder = new ThreadLocal<>();


    @Pointcut("@annotation(com.f.datasource.annotations.DataSource)")
    protected void datasourceAspect() {

    }



    /**
     * 根据@DataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
     */
    @Before("datasourceAspect()")
    public void changeDataSourceBeforeMethodExecution(JoinPoint jp) {
        String key = determineDatasource(jp);
        if (key == null) {
            DataSourceContextHolder.setDatasourceType(null);
            log.warn("数据源不存在，使用默认数据源 >" +key+"  "+jp.getSignature());
            return;
        }
        preDatasourceHolder.set(DataSourceContextHolder.getDatasourceType());
        DataSourceContextHolder.setDatasourceType(key);
        log.warn("数据源存在，使用数据源 > "+key+" "+jp.getSignature());
    }

    /**
     *
     * @param jp
     * @return
     */
    public String determineDatasource(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class targetClass = jp.getSignature().getDeclaringType();
        String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
        String dataSourceForTargetMethod = resolveDataSourceFromMethod(targetClass, methodName);
        String resultDS = determinateDataSource(dataSourceForTargetClass, dataSourceForTargetMethod);
        return resultDS;
    }


    /**
     *
     */
    @After("datasourceAspect()")
    public void restoreDataSourceAfterMethodExecution() {
        DataSourceContextHolder.setDatasourceType(preDatasourceHolder.get());
        preDatasourceHolder.remove();
    }


    /**
     *
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
     *
     * @param classDS
     * @param methodDS
     * @return
     */
    private String determinateDataSource(String classDS, String methodDS) {
        return methodDS == null ? classDS : methodDS;
    }

    /**
     *
     * @param targetClass
     * @return
     */
    private String resolveDataSourceFromClass(Class targetClass) {
        DataSource classAnnotation = (DataSource) targetClass.getAnnotation(DataSource.class);
        return null != classAnnotation ? resolveDataSourceName(classAnnotation) : null;
    }

    /**
     *
     * @param ds
     * @return
     */
    private String resolveDataSourceName(DataSource ds) {
        return ds == null ? null : ds.value();
    }

    /**
     *
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
}
