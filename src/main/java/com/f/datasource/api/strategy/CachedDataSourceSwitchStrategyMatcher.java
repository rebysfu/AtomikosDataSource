package com.f.datasource.api.strategy;

import com.f.datasource.api.aop.MethodInterceptorContext;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:01
 **/
@Log4j2
public abstract class CachedDataSourceSwitchStrategyMatcher implements DataSourceSwitchStrategyMatcher {

    static Map<CacheKey, Strategy> cache = new ConcurrentHashMap<>();

    public abstract Strategy createStrategyIfMatch(Class target, Method method);

    @Override
    public boolean match(Class target, Method method) {
        Strategy strategy = createStrategyIfMatch(target, method);
        if (null != strategy) {
            if (log.isDebugEnabled()) {
                log.debug("create data source switcher strategy:{} for method:{}", strategy, method);
            }
            CacheKey cacheKey = new CacheKey(target, method);
            cache.put(cacheKey, strategy);
            return true;
        }
        return false;
    }

    @Override
    public Strategy getStrategy(MethodInterceptorContext context) {
        Method method = context.getMethod();
        Class target = ClassUtils.getUserClass(context.getTarget());
        return cache.get(new CacheKey(target, method));
    }

    @AllArgsConstructor
    public static class CacheKey {

        private Class target;

        private Method method;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey target = ((CacheKey) obj);
            return target.target == this.target && target.method == method;
        }

        @Override
        public int hashCode() {
            int result = this.target != null ? this.target.hashCode() : 0;
            result = 31 * result + (this.method != null ? this.method.hashCode() : 0);
            return result;
        }
    }
}
