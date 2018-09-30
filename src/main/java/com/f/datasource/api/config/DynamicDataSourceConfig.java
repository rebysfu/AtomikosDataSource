package com.f.datasource.api.config;

import com.f.datasource.api.AbstractDataSourceConfiguration;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 上午11:33
 **/
@Data
public class DynamicDataSourceConfig extends AbstractDataSourceConfiguration implements Serializable {
    private static final long serialVersionUID = 2776152081818934459L;
    private String id;

    private String name;

    private String describe;
}
