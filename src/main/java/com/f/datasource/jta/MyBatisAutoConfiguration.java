package com.f.datasource.jta;

import com.f.datasource.jta.dynamic.DynamicDataSourceSqlSessionFactoryBuilder;
import com.f.datasource.jta.dynamic.DynamicSpringManagedTransaction;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-23 下午12:06
 **/
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class MyBatisAutoConfiguration {

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
    public MybatisProperties mybatisProperties() {
        return new MybatisProperties();
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        MybatisProperties mybatisProperties = this.mybatisProperties();
        factory.setVfs(SpringBootVFS.class);
        if (mybatisProperties().isDynamicDatasource()) {
            factory.setSqlSessionFactoryBuilder(new DynamicDataSourceSqlSessionFactoryBuilder());
            factory.setTransactionFactory(new SpringManagedTransactionFactory() {
                @Override
                public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
                    return new DynamicSpringManagedTransaction();
                }
            });
        }
        factory.setDataSource(dataSource);
        if (StringUtils.hasText(mybatisProperties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(mybatisProperties
                    .getConfigLocation()));
        }
        if (mybatisProperties.getConfiguration() != null) {
            factory.setConfiguration(mybatisProperties.getConfiguration());
        }
        if (this.interceptors != null && this.interceptors.length > 0) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        ///Users/rebys/IdeaProjects/admin-server/src/main/java/com/f/datasource/jta/handler
        factory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        String typeHandlers = "com.f.datasource.jta.handler";
        if (mybatisProperties.getTypeHandlersPackage() != null) {
            typeHandlers = typeHandlers + ";" + mybatisProperties.getTypeHandlersPackage();
        }
        factory.setTypeHandlersPackage(typeHandlers);
        factory.setMapperLocations(mybatisProperties.resolveMapperLocations());

        SqlSessionFactory sqlSessionFactory = factory.getObject();
        MybatisUtils.sqlSession = sqlSessionFactory;
        sqlSessionFactory.getConfiguration().getTypeAliasRegistry();

        return sqlSessionFactory;
    }
}