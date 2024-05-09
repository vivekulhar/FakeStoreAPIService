package dev.vivek.productservicetutorial.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfigs {

    @Bean
    RedisTemplate<Long, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<Long, Object > template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
