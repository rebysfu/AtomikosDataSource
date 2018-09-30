package com.f.datasource.api.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 上午11:35
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class InSpringDynamicDataSourceConfig extends DynamicDataSourceConfig {
    private static final long serialVersionUID = -8434216403009495774L;

    private String beanName;
}
