package com.f.configure.mybatis;

import com.f.base.AbstractMybatisConfig;
import com.f.constants.DataSourceKey;
import com.f.datasource.DynamicSqlSessionTemplate;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-27 上午10:48
 **/
@Configuration
@MapperScan(basePackages = "com.f.mvc.mapper.antispam", sqlSessionFactoryRef = "sqlSessionFactoryAntispam")
public class AntispamConfig extends AbstractMybatisConfig {

    @Bean(name = "sqlSessionFactoryAntispam")
    public SqlSessionFactory sqlSessionFactoryAntispam(@Qualifier(DataSourceKey.ANTISPAM) DataSource dataSource)
            throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }
}
