package com.f.datasource.api.service;

import com.f.datasource.api.DynamicDataSource;
import com.f.datasource.api.config.DynamicDataSourceConfig;
import com.f.datasource.api.exception.DataSourceClosedException;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CountDownLatch;

/**
 * @author rebysfu@gmail.com
 * @description：数据源缓存
 * @create 2018-09-20 上午11:43
 **/
@Log4j2
public class DataSourceCache {
    private long hash;

    private volatile boolean closed;

    private DynamicDataSource dataSource;

    private volatile CountDownLatch initLatch;
    private DynamicDataSourceConfig config;

    public DataSourceCache(long hash, DynamicDataSource dataSource, CountDownLatch initLatch, DynamicDataSourceConfig config) {
        this.hash = hash;
        this.dataSource = dataSource;
        this.initLatch = initLatch;
        this.config = config;
    }

    public long getHash() {
        return hash;
    }

    public DynamicDataSource getDataSource() {
        if (initLatch != null) {
            try {
                //等待初始化完成
                initLatch.await();
            } catch (Exception ignored) {
                log.warn(ignored.getMessage(), ignored);

            } finally {
                initLatch = null;
            }
        }
        if (closed) {
            throw new DataSourceClosedException(dataSource.getId());
        }
        return dataSource;
    }

    public boolean isClosed() {
        return closed;
    }


    public void closeDataSource() {
        closed = true;
    }

    public DynamicDataSourceConfig getConfig() {
        return config;
    }
}