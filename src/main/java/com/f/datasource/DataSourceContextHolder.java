package com.f.datasource;

import lombok.extern.log4j.Log4j2;

/**
 * @author rebysfu@gmail.com
 * @description： 提供切换数据源上下文
 * @create 2018-10-03 下午9:51
 **/
@Log4j2
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal();

    private static String defaultDataSourceKey = "";

    /**
     * 获取当前激活的数据源
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 根据数据源id切换数据源
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * 清除非默认数据源
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * 获取默认数据源
     *
     * @return data source key
     */
    public static String getDefaultDataSourceKey() {
        return defaultDataSourceKey;
    }
    /**
     * 设置默认数据源
     * */
    public static void setDefaultDataSourceKey(String key) {
        defaultDataSourceKey = key;
    }
}
