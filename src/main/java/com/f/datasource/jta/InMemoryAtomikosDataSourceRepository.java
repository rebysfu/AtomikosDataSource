package com.f.datasource.jta;

import com.google.common.collect.Maps;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:43
 **/
public class InMemoryAtomikosDataSourceRepository implements JtaDataSourceRepository {
    private Map<String, AtomikosDataSourceConfig> jta = Maps.newHashMap();
    @Resource
    private InMemoryAtomikosDataSourceProperties inMemoryAtomikosDataSourceProperties;

    @PostConstruct
    public void init() {
        jta = inMemoryAtomikosDataSourceProperties.getJta();
        jta.forEach((id, config) -> {
            if (config.getId() == null) {
                config.setId(id);
            } else if (!config.getId().equals(id)) {
                jta.put(config.getId(), config);
            }
        });
    }

    @Override
    public Collection<AtomikosDataSourceConfig> findAll() {
        return jta.values();
    }

    @Override
    public AtomikosDataSourceConfig findById(String dataSourceId) {
        return jta.get(dataSourceId);
    }

    @Override
    public AtomikosDataSourceConfig add(AtomikosDataSourceConfig config) {
        return jta.put(config.getId(), config);
    }

    @Override
    public AtomikosDataSourceConfig remove(String dataSourceId) {
        return jta.remove(dataSourceId);
    }
}
