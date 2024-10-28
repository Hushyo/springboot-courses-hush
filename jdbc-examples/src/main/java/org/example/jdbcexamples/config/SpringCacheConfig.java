package org.example.jdbcexamples.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class SpringCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        // 创建 caffeine 缓存管理器
        Cache<Object,Object> cache = Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(100)
                .build();

        manager.registerCustomCache("users",cache);
        manager.setAllowNullValues(true);
        return manager;
    }
}
