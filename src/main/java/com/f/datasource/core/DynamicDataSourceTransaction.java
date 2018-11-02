package com.f.datasource.core;

import com.f.datasource.DataSourceContextHolder;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

/**
 * User: bvsoo
 * Date: 2018/10/29
 * Time: 4:43 PM
 */
@Log4j2
public class DynamicDataSourceTransaction implements Transaction {

    private final DataSource dataSource;
    private Connection mainConnection;
    private String mainDatabaseKey;
    private Map<String, Connection> otherConnectionMap;
    private Map<String, Connection> mainConnectionMap;
    private boolean isConnectionTransactional;
    private boolean autoCommit;

    public DynamicDataSourceTransaction(DataSource dataSource) {
        notNull(dataSource, "No DataSource specified");
        this.dataSource = dataSource;
        otherConnectionMap = Maps.newConcurrentMap();
        mainConnectionMap = Maps.newConcurrentMap();
        mainDatabaseKey = DataSourceContextHolder.getDefaultDataSourceKey();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() throws SQLException {
        String dataSourceKey = DataSourceContextHolder.getDataSourceKey();
        if (dataSourceKey.equals(mainDatabaseKey)) {
            if (!mainConnectionMap.containsKey(dataSourceKey)) {
                openMainConnection();
            }
            return mainConnectionMap.get(dataSourceKey);
        } else {
            if (!otherConnectionMap.containsKey(dataSourceKey)) {
                try {
                    Connection conn = dataSource.getConnection();
                    conn.setAutoCommit(autoCommit);
                    mainConnection = conn;
                    otherConnectionMap.put(dataSourceKey, conn);
                } catch (SQLException ex) {
                    throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
                }
            }
            return otherConnectionMap.get(dataSourceKey);
        }

    }


    private void openMainConnection() throws SQLException {
        this.mainConnection = DataSourceUtils.getConnection(this.dataSource);
        this.autoCommit = this.mainConnection.getAutoCommit();
        this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.mainConnection, this.dataSource);
        mainConnectionMap.put(mainDatabaseKey, mainConnection);
        if (!otherConnectionMap.isEmpty()) {
            isConnectionTransactional = false;
        }
        if (log.isDebugEnabled()) {
            log.debug(
                    "JDBC Connection ["
                            + this.mainConnection
                            + "] will"
                            + (this.isConnectionTransactional ? " " : " not ")
                            + "be managed by Spring");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() throws SQLException {
        if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            if (mainConnectionMap.get(mainDatabaseKey) != null) {
                mainConnectionMap.get(mainDatabaseKey).commit();
            }
            for (Connection connection : otherConnectionMap.values()) {
                if (log.isDebugEnabled()) {
                    log.debug("Committing JDBC Connection [" + connection + "]");
                }
                connection.commit();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollback() throws SQLException {
        if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            if (mainConnectionMap.get(mainDatabaseKey) != null) {
                mainConnectionMap.get(mainDatabaseKey).rollback();
            }
            for (Connection connection : otherConnectionMap.values()) {
                if (log.isDebugEnabled()) {
                    log.debug("Rolling back JDBC Connection [" + connection + "]");
                }
                connection.rollback();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws SQLException {
        if (mainConnectionMap.get(mainDatabaseKey) != null) {
            DataSourceUtils.releaseConnection(mainConnectionMap.get(mainDatabaseKey), this.dataSource);
        }
        for (Connection connection : otherConnectionMap.values()) {
            DataSourceUtils.releaseConnection(connection, this.dataSource);
        }
    }

    @Override
    public Integer getTimeout() throws SQLException {
        return null;
    }
}