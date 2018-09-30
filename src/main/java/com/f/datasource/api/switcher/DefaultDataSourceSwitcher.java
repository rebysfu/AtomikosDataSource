package com.f.datasource.api.switcher;

import com.f.datasource.api.utils.ThreadLocalUtils;
import lombok.extern.log4j.Log4j2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author rebysfu@gmail.com
 * @description：默认的动态数据源切换器,基于ThreadLocal,queue
 * @create 2018-09-20 下午12:40
 **/
@Log4j2
public class DefaultDataSourceSwitcher implements DataSourceSwitcher {

    //默认数据源标识
    private static final String DEFAULT_DATASOURCE_ID = DataSourceSwitcher.class.getName() + "_default_";

    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Deque<String> getUsedHistoryQueue() {
        // 从ThreadLocal中获取一个使用记录
        return ThreadLocalUtils.get(DefaultDataSourceSwitcher.class.getName() + "_queue", LinkedList::new);
    }

    @Override
    public void useLast() {
        // 没有上一次了
        if (getUsedHistoryQueue().size() == 0) {
            return;
        }
        //移除队尾,则当前的队尾则为上一次的数据源
        getUsedHistoryQueue().removeLast();
        if (log.isDebugEnabled()) {
            String current = currentDataSourceId();
            if (null != current) {
                log.debug("try use last data source : {}", currentDataSourceId());
            } else {
                log.debug("try use default data source");
            }
        }
    }

    @Override
    public void use(String dataSourceId) {
        //添加对队尾
        getUsedHistoryQueue().addLast(dataSourceId);
        if (log.isDebugEnabled()) {
            log.debug("try use data source : {}", dataSourceId);
        }
    }

    @Override
    public void useDefault() {
        getUsedHistoryQueue().addLast(DEFAULT_DATASOURCE_ID);
        if (log.isDebugEnabled()) {
            log.debug("try use default data source");
        }
    }

    @Override
    public String currentDataSourceId() {
        if (getUsedHistoryQueue().size() == 0) {
            return null;
        }

        String activeId = getUsedHistoryQueue().getLast();
        if (DEFAULT_DATASOURCE_ID.equals(activeId)) {
            return null;
        }
        return activeId;
    }

    @Override
    public void reset() {
        getUsedHistoryQueue().clear();
        if (log.isDebugEnabled()) {
            log.debug("reset data source used history");
        }
    }
}
