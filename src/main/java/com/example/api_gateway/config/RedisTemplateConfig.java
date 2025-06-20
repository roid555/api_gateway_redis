package com.example.api_gateway.config;

import com.example.api_gateway.DTO.AppUserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



@Configuration
public class RedisTemplateConfig {


    @Bean
    public RedisTemplate<String, AppUserDTO> userRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, AppUserDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;


    }

}
