package fa11er.mediamtx.authenticator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Value("${redis.ttl.duration-of-minutes:10}")
    private  int redisTtlDurationOfMinutes;
    @Bean
    public org.springframework.data.redis.cache.RedisCacheConfiguration cacheConfiguration() {
        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(redisTtlDurationOfMinutes))
                .disableCachingNullValues();

    }

}

