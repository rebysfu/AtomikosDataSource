package com.f.datasource.api;

import com.f.datasource.api.aop.MethodInterceptorHolder;
import com.f.datasource.api.exception.DataSourceNotFoundException;
import com.f.datasource.api.strategy.AnnotationDataSourceSwitchStrategyMatcher;
import com.f.datasource.api.strategy.CachedDataSourceSwitchStrategyMatcher;
import com.f.datasource.api.strategy.DataSourceSwitchStrategyMatcher;
import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.f.datasource.api.strategy.AnnotationDataSourceSwitchStrategyMatcher.CacheKey;
import static com.f.datasource.api.strategy.AnnotationDataSourceSwitchStrategyMatcher.Strategy;

/**
 * @author rebysfu@gmail.com
 * @description：通过aop方式进行对注解方式切换数据源提供支持
 * @create 2018-09-20 下午12:51
 **/
@Configuration
@Log4j2
public class AopDataSourceSwitcherAutoConfiguration {

    @Bean
    public AnnotationDataSourceSwitchStrategyMatcher annotationDataSourceSwitchStrategyMatcher() {
        return new AnnotationDataSourceSwitchStrategyMatcher();
    }

    @Bean
    public SwitcherMethodMatcherPointcutAdvisor switcherMethodMatcherPointcutAdvisor(List<DataSourceSwitchStrategyMatcher> matchers) {
        return new SwitcherMethodMatcherPointcutAdvisor(matchers);
    }

    /*
     * @Author rebysfu@gmail.com
     * @Description  定义切面内部类
     * @Date 下午2:06 2018/9/22
     **/
    @Log4j2
    public static class SwitcherMethodMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
        //private static final Logger logger           = LoggerFactory.getLogger(SwitcherMethodMatcherPointcutAdvisor.class);
        private static final long serialVersionUID = 536295121851990398L;

        private List<DataSourceSwitchStrategyMatcher> matchers;

        private Map<AnnotationDataSourceSwitchStrategyMatcher.CacheKey, DataSourceSwitchStrategyMatcher> cache = new ConcurrentHashMap<>();

        public SwitcherMethodMatcherPointcutAdvisor(List<DataSourceSwitchStrategyMatcher> matchers) {
            this.matchers = matchers;
            setAdvice((MethodInterceptor) methodInvocation -> {
                CacheKey key = new CachedDataSourceSwitchStrategyMatcher.CacheKey(ClassUtils.getUserClass(methodInvocation.getThis()), methodInvocation.getMethod());
                DataSourceSwitchStrategyMatcher matcher = cache.get(key);
                if (matcher == null) {
                    log.warn("method:{} not support switch datasource", methodInvocation.getMethod());
                } else {
                    MethodInterceptorHolder holder = MethodInterceptorHolder.create(methodInvocation);
                    Strategy strategy = matcher.getStrategy(holder.createParamContext());
                    if (strategy == null) {
                        log.warn("strategy matcher found:{}, but strategy is null!", matcher);
                    } else {
                        log.debug("switch datasource.use strategy:{}", strategy);
                        if (strategy.isUseDefaultDataSource()) {
                            DataSourceHolder.switcher().useDefault();
                        } else {
                            String id = strategy.getDataSourceId();
                            if (!DataSourceHolder.existing(id)) {
                                if (strategy.isFallbackDefault()) {
                                    DataSourceHolder.switcher().useDefault();
                                } else {
                                    throw new DataSourceNotFoundException(id);
                                }
                            } else {
                                DataSourceHolder.switcher().use(id);
                            }
                        }
                    }
                }
                try {
                    return methodInvocation.proceed();
                } finally {
                    DataSourceHolder.switcher().useLast();
                }
            });
        }

        @Override
        public boolean matches(Method method, Class<?> aClass) {
            CacheKey key = new CacheKey(aClass, method);
            matchers.stream()
                    .filter(matcher -> matcher.match(aClass, method))
                    .findFirst()
                    .ifPresent((matcher) -> cache.put(key, matcher));
            return cache.containsKey(key);
        }
    }
}