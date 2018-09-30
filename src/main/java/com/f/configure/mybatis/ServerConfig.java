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
 * @create 2018-09-27 上午10:48
 **/
@Configuration
@MapperScan(basePackages = "com.f.mvc.mapper.server", sqlSessionFactoryRef = "sqlSessionFactoryServer")
public class ServerConfig extends AbstractMybatisConfig {

    @Bean(name = "sqlSessionFactoryServer")
    public SqlSessionFactory sqlSessionFactoryServer(@Qualifier(DataSourceKey.FISH2_SERVER) DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }

}
