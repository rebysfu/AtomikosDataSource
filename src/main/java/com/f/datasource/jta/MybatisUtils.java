package com.f.datasource.jta;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-23 下午12:10
 **/
public class MybatisUtils {
    volatile static SqlSessionFactory sqlSession;

    public static ResultMap getResultMap(String id) {
        return getSqlSession().getConfiguration().getResultMap(id);
    }

    public static SqlSessionFactory getSqlSession() {
        if (sqlSession == null) {
            throw new UnsupportedOperationException("sqlSession is null");
        }
        return sqlSession;
    }
}
