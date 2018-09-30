package com.f.datasource.jta;

import com.f.datasource.api.DynamicDataSource;
import com.f.datasource.api.DynamicDataSourceProxy;
import com.f.datasource.api.config.DynamicDataSourceConfigRepository;
import com.f.datasource.api.service.AbstractDynamicDataSourceService;
import com.f.datasource.api.service.DataSourceCache;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:40
 **/
@Log4j2
public class JtaDynamicDataSourceService extends AbstractDynamicDataSourceService<AtomikosDataSourceConfig> {
    private Executor executor = Executors.newFixedThreadPool(10);

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JtaDynamicDataSourceService(DynamicDataSourceConfigRepository<AtomikosDataSourceConfig> repository, DynamicDataSource defaultDataSource) {
        super(repository, defaultDataSource);
    }

    public JtaDynamicDataSourceService(DynamicDataSourceConfigRepository<AtomikosDataSourceConfig> repository, DataSource dataSource) throws SQLException {
        super(repository, dataSource);
    }

    @Autowired(required = false)
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }


    @Override
    @SneakyThrows
    protected DataSourceCache createCache(AtomikosDataSourceConfig config) {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        config.putProperties(atomikosDataSourceBean);
        atomikosDataSourceBean.setBeanName("dynamic_ds_" + config.getId());
        atomikosDataSourceBean.setUniqueResourceName("dynamic_ds_" + config.getId());
        AtomicInteger successCounter = new AtomicInteger();
        CountDownLatch downLatch = new CountDownLatch(1);
        DataSourceCache cache = new DataSourceCache(config.hashCode(), new DynamicDataSourceProxy(config.getId(), atomikosDataSourceBean), downLatch, config) {
            @Override
            public void closeDataSource() {
                super.closeDataSource();
                atomikosDataSourceBean.close();
                XADataSource dataSource = atomikosDataSourceBean.getXaDataSource();
                if (dataSource instanceof Closeable) {
                    try {
                        ((Closeable) dataSource).close();
                    } catch (IOException e) {
                        log.error("close xa datasource error", e);
                    }
                } else {
                    log.warn("XADataSource is not instanceof Closeable!", (Object) Thread.currentThread().getStackTrace());
                }
            }
        };
        //异步初始化
        executor.execute(() -> {
            try {
                atomikosDataSourceBean.init();
                successCounter.incrementAndGet();
                downLatch.countDown();
            } catch (Exception e) {
                log.error("init datasource {} error", config.getId(), e);

                //atomikosDataSourceBean.close();
            }
        });
        //初始化状态判断
        executor.execute(() -> {
            try {
                Thread.sleep(config.getInitTimeout() * 1000L);
            } catch (InterruptedException ignored) {
                log.warn(ignored.getMessage(), ignored);
                Thread.currentThread().interrupt();
            } finally {
                if (successCounter.get() == 0) {
                    // 初始化超时,认定为失败
                    log.error("init timeout ({}ms)", config.getInitTimeout());
                    cache.closeDataSource();
                    if (downLatch.getCount() > 0) {
                        downLatch.countDown();
                    }
                }
            }
        });
        return cache;
    }
}
