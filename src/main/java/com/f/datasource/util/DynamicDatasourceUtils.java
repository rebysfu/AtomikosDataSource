package com.f.datasource.util;

import com.f.datasource.configure.DynamicDatasourceConfig;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

/**
 * @author rebysfu@gmail.com
 * @description：工具类
 * @create 2018-10-05 下午4:00
 **/
public class DynamicDatasourceUtils {
    /**
     * 设置数据源各项属性
     *
     * @param dynamicDatasourceConfig
     * @param dataSource
     */
    public static void setDsProperties(DynamicDatasourceConfig dynamicDatasourceConfig, DataSource dataSource) {

        Set<Map.Entry<String, Object>> entries = beanToMap(dynamicDatasourceConfig).entrySet();
        for (Map.Entry<String, Object> entry : entries) {

            String name = entry.getKey();
            PropertyDescriptor propertyDescriptor = ClassUtils.getPropertyDescriptor(dataSource.getClass(), name);
            if (propertyDescriptor == null || propertyDescriptor.getWriteMethod() == null) {
                continue;
            }

            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
            }
            Object value = ClassUtils.toTargetTypeValue(entry.getValue(), propertyDescriptor.getPropertyType());
            ClassUtils.invokeMethod(writeMethod, dataSource, value);
        }
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;

    }
}
