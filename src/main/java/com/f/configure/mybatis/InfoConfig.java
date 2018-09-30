package com.f.configure.mybatis;

import com.f.base.AbstractMybatisConfig;
import com.f.constants.DataSourceKey;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-28 下午12:11
 **/
@Configuration
@MapperScan(basePackages = "com.f.mvc.mapper.info", sqlSessionFactoryRef = "sqlSessionFactoryInfo")
public class InfoConfig extends AbstractMybatisConfig {

    @Bean(name = "sqlSessionFactoryInfo")
    public SqlSessionFactory sqlSessionFactoryInfo(@Qualifier(DataSourceKey.FISH2_INFO) DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }
}
