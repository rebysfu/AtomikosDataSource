package com.f.configure;

import com.f.datasource.core.DynamicRedisTemplate;
import com.f.datasource.properties.DynamicRedisProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * User: bvsoo
 * Date: 2018/10/22
 * Time: 3:46 PM
 * <p>
 * <p>
 * <!-- Channel设置 -->
 * <bean id="payChannelTopic" class="org.springframework.data.redis.listener.ChannelTopic">
 * <constructor-arg value="pay:finish" />
 * </bean>
 * <bean id="tunnelChannelTopic" class="org.springframework.data.redis.listener.ChannelTopic">
 * <constructor-arg value="tunnel:update" />
 * </bean>
 */
@Log4j2
@AutoConfigureAfter(value = DynamicRedisProperties.class)
@Configuration
public class DynamicRedisConfigurer {
    @Autowired
    private DynamicRedisProperties dynamicRedisProperties;

    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate() {
        return new DynamicRedisTemplate<>(dynamicRedisProperties);
    }
}
