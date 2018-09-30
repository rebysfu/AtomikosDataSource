package com.f.datasource.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-26 下午12:56
 **/
@Getter
@Setter
public class DruidDataSourceProperties{
    private String url;
    private String username;
    private String password;
    //这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName
    private String driverClassName;
    //初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
    private int initialSize;
    //最小连接池数量
    private int minIdle=1;
    //最大连接池数量
    private int maxActive=8;
    //获取连接时最大等待时间，单位毫秒。配置了maxWait之后，
    // 缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery="SELECT 1";
    private boolean testWhileIdle;
    private boolean testOnBorrow=true;
    private boolean testOnReturn;
    //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
    private boolean poolPreparedStatements;
    //要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
    // 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
    private int maxPoolPreparedStatementPerConnectionSize=-1;
    private String filters;
    private String connectionProperties;
}
