package com.samsungsds.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisCacheConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        Map<String, RedisCacheConfiguration> config = new HashMap<>();

        config.put("groupMemberCache",
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(60)));   // auto-refresh every 30 mins

        return RedisCacheManager.builder(connectionFactory)
                .withInitialCacheConfigurations(config)
                .build();
    }
}



