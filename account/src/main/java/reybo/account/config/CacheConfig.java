package reybo.account.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String ACCOUNT_CACHE = "account";
    public static final String ACCOUNT_STATUS_CACHE = "account_status";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(
                ACCOUNT_CACHE,
                ACCOUNT_STATUS_CACHE
        );

        // Можно настроить TTL (Time To Live) для кэшей
        // В будущем лучше Redis
        return cacheManager;
    }
}
