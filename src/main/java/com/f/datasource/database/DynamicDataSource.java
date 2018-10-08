package com.f.datasource.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.f.datasource.DataSourceContextHolder;
import com.f.datasource.configure.DynamicDatasourceConfig;
import com.f.datasource.properties.DynamicDataSourceProperties;
import com.f.datasource.util.ClassUtils;
import com.f.datasource.util.DynamicDatasourceUtils;
import com.f.enums.DataSourceKey;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yu on 2017/3/21.
 */
@Log4j2
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final static String DATASOURCE_CLASS = "com.alibaba.druid.pool.DruidDataSource";
    private String defalutDataSourceId;
    @Resource
    private DynamicDataSourceProperties properties;

    /**
     * 取得当前使用那个数据源。
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.warn("Current DataSource is [{}]", StringUtils.isEmpty(DataSourceContextHolder.getDataSourceKey()) ? defalutDataSourceId : DataSourceContextHolder.getDataSourceKey());
        return DataSourceContextHolder.getDataSourceKey();
    }

    @Override
    public void afterPropertiesSet() {
        this.initDataSources();
    }

    private void initDataSources() {
        log.debug("Data source initialization start");
        Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
        Object defaultTargetDataSource = null;
        Map<String, DynamicDatasourceConfig> map = properties.getDatasource();
        for (Map.Entry<String, DynamicDatasourceConfig> entry : map.entrySet()) {
            String dataSourceId = entry.getKey();
            if (DataSourceKey.isNotDefined(dataSourceId)) {
                throw new RuntimeException("The DataSource【 " + dataSourceId + " 】Didn't find in " + DataSourceKey.class);
            }
            DynamicDatasourceConfig dynamicDatasourceConfig = entry.getValue();
            boolean isDefaultDataSource = dynamicDatasourceConfig.isDefalut();
            DruidDataSource dataSource = (DruidDataSource) ClassUtils.newInstance(DATASOURCE_CLASS);
            DynamicDatasourceUtils.setDsProperties(dynamicDatasourceConfig, dataSource);
            targetDataSource.put(dataSourceId, dataSource);
            if (Boolean.valueOf(isDefaultDataSource)) {
                defaultTargetDataSource = dataSource;
                defalutDataSourceId = dataSourceId;
            }
            log.info("dataSourceId={},dataSourceClass={},isDefaultDataSource={}", new Object[]{
                    dataSourceId, DATASOURCE_CLASS, isDefaultDataSource});
        }
        if (defaultTargetDataSource == null) {
            throw new RuntimeException("Data source configuration errors, you must set a default data source, such as: a new defalut attribute in the configuration file, the value is true");
        }
        this.setTargetDataSources(targetDataSource);
        this.setDefaultTargetDataSource(defaultTargetDataSource);
        super.afterPropertiesSet();
        log.debug("Data source initialization is complete");

    }


}
