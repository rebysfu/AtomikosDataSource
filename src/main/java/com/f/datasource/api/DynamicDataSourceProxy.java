package com.f.datasource.api;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author rebysfu@gmail.com
 * @description：动态数据源代理,将数据源代理为动态数据源
 * @create 2018-09-20 上午11:53
 **/
public class DynamicDataSourceProxy implements DynamicDataSource {
    private String id;

    private volatile DatabaseType databaseType;

    private DataSource proxy;

    private Lock lock = new ReentrantLock();

    public DynamicDataSourceProxy(String id, DatabaseType databaseType, DataSource proxy) {
        this.id = id;
        this.databaseType = databaseType;
        this.proxy = proxy;
    }

    public DynamicDataSourceProxy(String id, DataSource proxy) {
        this.id = id;
        this.proxy = proxy;
    }

    @Override
    public DataSource getNative() {
        return proxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public DatabaseType getType() {
        if (databaseType == null) {
            lock.lock();
            try {
                if (databaseType != null) {
                    return databaseType;
                }
                try (Connection connection = proxy.getConnection()) {
                    databaseType = DatabaseType.fromJdbcUrl(connection.getMetaData().getURL());
                }
            } catch (SQLException e) {
                throw new UnsupportedOperationException(e);
            } finally {
                lock.unlock();
            }
        }

        return databaseType;
    }
}
