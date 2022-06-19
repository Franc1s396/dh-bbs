package org.francis.dh.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author Franc1s
 * @date 2022/6/5
 * @apiNote
 */
@Configuration
public class RedisConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> keySerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        keySerializer.setObjectMapper(objectMapper);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(keySerializer);
        return template;
    }
}
