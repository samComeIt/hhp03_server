package hh.plus.server.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))  // Set default TTL of 1 hour
                .disableCachingNullValues();

        // Specific cache configuration for popularProductsCache
        RedisCacheConfiguration popularProductsCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(2))  // Specific TTL of 2 hours for popularProductsCache
                .disableCachingNullValues();

        // Specific cache configuration for cartCache
        RedisCacheConfiguration cartCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))  // Specific TTL of 30 minutes for cartCache
                .disableCachingNullValues();

        // Specific cache configuration for orderCache
        RedisCacheConfiguration orderCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))  // Specific TTL of 30 minutes for cartCache
                .disableCachingNullValues();

        // Specific cache configuration for orderCache
        RedisCacheConfiguration orderSheetCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))  // Specific TTL of 30 minutes for cartCache
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)  // Default configuration
                .withCacheConfiguration("cartCache", cartCacheConfig)  // Cache configuration for cartCache
                .withCacheConfiguration("popularProductsCache", popularProductsCacheConfig)  // Cache configuration for popularProductsCache
                .withCacheConfiguration("orderCache", orderCacheConfig)  // Cache configuration for orderCache
                .withCacheConfiguration("orderSheetCache", orderSheetCacheConfig)  // Cache configuration for orderSheetCache
                .build();
    }

}
