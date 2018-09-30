package com.f.base;

import com.f.constants.DataSourceKey;
import com.f.datasource.DynamicSqlSessionTemplate;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-28 下午12:16
 **/
public abstract class AbstractMybatisConfig {
    /**
     * 创建数据源
     *
     * @param dataSource
     * @return
     */
    protected SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        return bean.getObject();
    }
    @Bean(name = "sqlSessionTemplate")
    public DynamicSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryMaster") SqlSessionFactory sqlSessionFactoryMaster,
                                                        @Qualifier("sqlSessionFactoryInfo") SqlSessionFactory sqlSessionFactoryInfo,
                                                        @Qualifier("sqlSessionFactoryServer") SqlSessionFactory sqlSessionFactoryServer,
                                                        @Qualifier("sqlSessionFactoryOrder") SqlSessionFactory sqlSessionFactoryOrder,
                                                        @Qualifier("sqlSessionFactoryRecord") SqlSessionFactory sqlSessionFactoryRecord,
                                                        @Qualifier("sqlSessionFactoryPromotion") SqlSessionFactory sqlSessionFactoryPromotion,
                                                        @Qualifier("sqlSessionFactoryAntispam") SqlSessionFactory sqlSessionFactoryAntispam
    ) {
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put(DataSourceKey.MASTER, sqlSessionFactoryMaster);
        sqlSessionFactoryMap.put(DataSourceKey.FISH2_INFO, sqlSessionFactoryInfo);
        sqlSessionFactoryMap.put(DataSourceKey.FISH2_SERVER, sqlSessionFactoryServer);
        sqlSessionFactoryMap.put(DataSourceKey.FISH2_ORDER, sqlSessionFactoryOrder);
        sqlSessionFactoryMap.put(DataSourceKey.POKER_RECORD, sqlSessionFactoryRecord);
        sqlSessionFactoryMap.put(DataSourceKey.PROMOTION, sqlSessionFactoryPromotion);
        sqlSessionFactoryMap.put(DataSourceKey.ANTISPAM, sqlSessionFactoryAntispam);
        DynamicSqlSessionTemplate dynamicSqlSessionTemplate = new DynamicSqlSessionTemplate(sqlSessionFactoryMaster);
        dynamicSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return dynamicSqlSessionTemplate;
    }
}
