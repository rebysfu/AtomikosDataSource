package com.f.datasource.jta;

import com.f.datasource.api.config.DynamicDataSourceConfigRepository;

/**
 * @author rebysfu@gmail.com
 * @description：如果需要将数据源配置放到数据库中或其他地方,实现 JtaDataSourceRepository 接口并注入到spring容器即可
 * @create 2018-09-20 下午1:34
 **/
public interface JtaDataSourceRepository extends DynamicDataSourceConfigRepository<AtomikosDataSourceConfig> {
}
