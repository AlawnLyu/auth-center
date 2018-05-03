package com.lyu.auth.config.redis;

import com.lyu.auth.util.redis.RedisCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisCache redisCache() {
        return new RedisCache();
    }
}
