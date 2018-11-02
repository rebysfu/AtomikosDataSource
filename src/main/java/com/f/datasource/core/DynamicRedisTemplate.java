package com.f.datasource.core;

import com.f.datasource.configure.DynamicRedisConfig;
import com.f.datasource.properties.DynamicRedisProperties;
import com.f.enums.RedisKey;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * User: bvsoo
 * Date: 2018/10/22
 * Time: 3:43 PM
 */
@Log4j2
public class DynamicRedisTemplate<K, V> extends AbstractRoutingRedisTemplate<K, V> {

    /**
     * 线程本地变量保存redis
     */
    private static final ThreadLocal<Pair<Integer, Integer>> contextLocal = new ThreadLocal<>();

    private DynamicRedisProperties dynamicRedisProperties;

    public DynamicRedisTemplate(DynamicRedisProperties dynamicRedisProperties) {
        this.dynamicRedisProperties = dynamicRedisProperties;
    }

    public DynamicRedisTemplate() {
    }

    public static Pair<Integer, Integer> getCurrentDbIndex() {
        return contextLocal.get();
    }

    public static void setCurrentDbIndex(Pair<Integer, Integer> pair) {
        contextLocal.set(pair);
        log.info("Set current redis db :" + pair.getLeft() + "-" + pair.getRight());
    }

    public static void remove() {
        contextLocal.remove();
    }

    @Override
    protected Pair<Integer, Integer> determineCurrentLookupKey() {
        Pair<Integer, Integer> pair = DynamicRedisTemplate.getCurrentDbIndex();
        return pair == null ? Pair.of(0, 0) : pair;
    }

    @Override
    protected RedisTemplate<K, V> createRedisTemplateOnMissing(Pair<Integer, Integer> pair) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(this.createConnectionFactory(pair));
        template.setDefaultSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        logger.debug("Create template for database:" + +pair.getLeft() + "-" + pair.getRight());
        return template;
    }


    private JedisConnectionFactory createConnectionFactory(Pair<Integer, Integer> pair) {
        RedisKey redisKey = RedisKey.fromIndex(pair.getLeft());
        DynamicRedisConfig dynamicRedisConfig = dynamicRedisProperties.getRedis().get(redisKey.getName());
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(dynamicRedisConfig.getHost());
        redisConnectionFactory.setPort(dynamicRedisConfig.getPort());
        redisConnectionFactory.setDatabase(pair.getRight());
        if (!Strings.isNullOrEmpty(dynamicRedisConfig.getPassword()))
            redisConnectionFactory.setPassword(dynamicRedisConfig.getPassword());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(dynamicRedisConfig.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(dynamicRedisConfig.getMaxWait());
        jedisPoolConfig.setMinIdle(0);
        redisConnectionFactory.setPoolConfig(jedisPoolConfig);
        redisConnectionFactory.afterPropertiesSet();
        return redisConnectionFactory;
    }
}
